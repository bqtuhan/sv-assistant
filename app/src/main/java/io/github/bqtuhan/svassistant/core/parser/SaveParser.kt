package io.github.bqtuhan.svassistant.core.parser

import android.util.Base64
import android.util.Base64InputStream
import io.github.bqtuhan.svassistant.data.model.FriendshipData
import io.github.bqtuhan.svassistant.data.model.PlayerData
import io.github.bqtuhan.svassistant.data.model.SaveGame
import io.github.bqtuhan.svassistant.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedInputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveParser @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun parse(inputStream: InputStream): SaveGame = withContext(ioDispatcher) {
        val buffered = BufferedInputStream(inputStream)
        
        // Peek the first 4 bytes to check for Base64 GZIP header ("H4sI")
        buffered.mark(4)
        val header = ByteArray(4)
        val read = buffered.read(header)
        buffered.reset()

        val isBase64Gzip = read == 4 && 
                header[0] == 'H'.code.toByte() && 
                header[1] == '4'.code.toByte() && 
                header[2] == 's'.code.toByte() && 
                header[3] == 'I'.code.toByte()

        // Stream directly through Base64 and GZIP decoders without loading into RAM
        val finalStream: InputStream = if (isBase64Gzip) {
            GZIPInputStream(Base64InputStream(buffered, Base64.DEFAULT))
        } else {
            buffered
        }

        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val parser = factory.newPullParser()
        parser.setInput(finalStream, null)
        parseSaveGame(parser)
    }

    private fun parseSaveGame(parser: XmlPullParser): SaveGame {
        var farmName = ""
        var gameVersion = "1.2"
        var currentSeason = "Spring"
        var dayOfMonth = 1
        var year = 1
        var millisecondsPlayed = 0L
        val players = mutableListOf<PlayerData>()

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                when (parser.name) {
                    "farmName" -> { farmName = parser.readSimpleText(); eventType = parser.next() }
                    "gameVersion" -> { 
                        val v = parser.readSimpleText()
                        if (v.isNotEmpty()) gameVersion = v
                        eventType = parser.next() 
                    }
                    "currentSeason" -> { currentSeason = parser.readSimpleText(); eventType = parser.next() }
                    "dayOfMonth" -> { dayOfMonth = parser.readSimpleText().toIntOrNull() ?: 1; eventType = parser.next() }
                    "year" -> { year = parser.readSimpleText().toIntOrNull() ?: 1; eventType = parser.next() }
                    "millisecondsPlayed" -> { millisecondsPlayed = parser.readSimpleText().toLongOrNull() ?: 0L; eventType = parser.next() }
                    "player" -> { 
                        players.add(parsePlayer(parser, true))
                        eventType = parser.eventType 
                        eventType = parser.next()
                    }
                    "farmhand", "Farmer" -> {
                        players.add(parsePlayer(parser, false))
                        eventType = parser.next()
                    }
                    else -> { parser.skip(); eventType = parser.next() }
                }
            } else {
                eventType = parser.next()
            }
        }

        return SaveGame(
            farmName = farmName,
            gameVersion = gameVersion,
            currentSeason = currentSeason,
            dayOfMonth = dayOfMonth,
            year = year,
            millisecondsPlayed = millisecondsPlayed,
            players = players        )
    }

    private fun parsePlayer(parser: XmlPullParser, isMain: Boolean): PlayerData {
        val startTagName = parser.name
        var name = ""
        var umid = "0"
        var totalMoneyEarned = 0L
        var houseUpgradeLevel = 0
        var spouse: String? = null
        var maxStamina = 270
        var deepestMineLevel = 0
        var experiencePoints = listOf<Int>()
        var stats = mapOf<String, String>()
        var mailReceived = setOf<String>()
        var eventsSeen = setOf<String>()
        var friendshipData = mapOf<String, FriendshipData>()

        var eventType = parser.next()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.END_TAG && parser.name == startTagName) break
            
            if (eventType == XmlPullParser.START_TAG) {
                when (parser.name) {
                    "name" -> { name = parser.readSimpleText(); eventType = parser.next() }
                    "UniqueMultiplayerID" -> { umid = parser.readSimpleText(); eventType = parser.next() }
                    "totalMoneyEarned" -> { totalMoneyEarned = parser.readSimpleText().toLongOrNull() ?: 0L; eventType = parser.next() }
                    "houseUpgradeLevel" -> { houseUpgradeLevel = parser.readSimpleText().toIntOrNull() ?: 0; eventType = parser.next() }
                    "spouse" -> { spouse = parser.readSimpleText().ifEmpty { null }; eventType = parser.next() }
                    "maxStamina" -> { maxStamina = parser.readSimpleText().toIntOrNull() ?: 270; eventType = parser.next() }
                    "deepestMineLevel" -> { deepestMineLevel = parser.readSimpleText().toIntOrNull() ?: 0; eventType = parser.next() }
                    "experiencePoints" -> { experiencePoints = parseIntList(parser); eventType = parser.next() }
                    "stats" -> { stats = parseStats(parser); eventType = parser.next() }
                    "mailReceived" -> { mailReceived = parseStringList(parser); eventType = parser.next() }
                    "eventsSeen" -> { eventsSeen = parseStringList(parser); eventType = parser.next() }
                    "friendshipData" -> { friendshipData = parseFriendships(parser); eventType = parser.next() }
                    else -> { parser.skip(); eventType = parser.next() }
                }
            } else {
                eventType = parser.next()
            }
        }

        return PlayerData(
            name = name,
            uniqueMultiplayerId = umid,
            isMainPlayer = isMain,
            totalMoneyEarned = totalMoneyEarned,
            houseUpgradeLevel = houseUpgradeLevel,
            spouse = spouse,            maxStamina = maxStamina,
            deepestMineLevel = deepestMineLevel,
            experiencePoints = experiencePoints,
            stats = stats,
            mailReceived = mailReceived,
            eventsSeen = eventsSeen,
            friendshipData = friendshipData
        )
    }

    private fun parseStats(parser: XmlPullParser): Map<String, String> {
        val stats = mutableMapOf<String, String>()
        val startTagName = parser.name
        var eventType = parser.next()
        
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.END_TAG && parser.name == startTagName) break
            
            if (eventType == XmlPullParser.START_TAG) {
                if (parser.name == "item") {
                    var key = ""
                    var value = ""
                    var itemEvent = parser.next()
                    while (itemEvent != XmlPullParser.END_DOCUMENT) {
                        if (itemEvent == XmlPullParser.END_TAG && parser.name == "item") break
                        if (itemEvent == XmlPullParser.START_TAG) {
                            if (parser.name == "key") {
                                var kEvent = parser.next()
                                while (kEvent != XmlPullParser.END_DOCUMENT) {
                                    if (kEvent == XmlPullParser.END_TAG && parser.name == "key") break
                                    if (kEvent == XmlPullParser.START_TAG && parser.name == "string") {
                                        key = parser.readSimpleText()
                                        parser.next()
                                    }
                                    kEvent = parser.next()
                                }
                            } else if (parser.name == "value") {
                                var vEvent = parser.next()
                                while (vEvent != XmlPullParser.END_DOCUMENT) {
                                    if (vEvent == XmlPullParser.END_TAG && parser.name == "value") break
                                    if (vEvent == XmlPullParser.START_TAG) {
                                        value = parser.readSimpleText()
                                        parser.next()
                                    }
                                    vEvent = parser.next()
                                }
                            } else {
                                parser.skip()
                            }
                        }
                        itemEvent = parser.next()
                    }
                    if (key.isNotEmpty()) stats[key] = value
                } else if (parser.name != "Values") {
                    // Pre-1.6 format
                    val key = parser.name
                    val value = parser.readSimpleText()
                    stats[key] = value
                }
                eventType = parser.next()
            } else {
                eventType = parser.next()
            }
        }
        return stats
    }

    private fun parseStringList(parser: XmlPullParser): Set<String> {
        val set = mutableSetOf<String>()
        val startTagName = parser.name
        var eventType = parser.next()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.END_TAG && parser.name == startTagName) break
            if (eventType == XmlPullParser.START_TAG) {
                if (parser.name == "string" || parser.name == "int" || parser.name == "long") {
                    set.add(parser.readSimpleText())
                } else {
                    parser.skip()
                }
                eventType = parser.next()
            } else {
                eventType = parser.next()
            }
        }
        return set
    }

    private fun parseIntList(parser: XmlPullParser): List<Int> {
        val list = mutableListOf<Int>()
        val startTagName = parser.name
        var eventType = parser.next()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.END_TAG && parser.name == startTagName) break
            if (eventType == XmlPullParser.START_TAG) {
                if (parser.name == "int") {
                    list.add(parser.readSimpleText().toIntOrNull() ?: 0)
                } else {
                    parser.skip()
                }
                eventType = parser.next()            } else {
                eventType = parser.next()
            }
        }
        return list
    }

    private fun parseFriendships(parser: XmlPullParser): Map<String, FriendshipData> {
        val map = mutableMapOf<String, FriendshipData>()
        val startTagName = parser.name
        var eventType = parser.next()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.END_TAG && parser.name == startTagName) break
            if (eventType == XmlPullParser.START_TAG) {
                if (parser.name == "item") {
                    var who = ""
                    var points = 0
                    var status = "Friendly"
                    var itemEvent = parser.next()
                    while (itemEvent != XmlPullParser.END_DOCUMENT) {
                        if (itemEvent == XmlPullParser.END_TAG && parser.name == "item") break
                        if (itemEvent == XmlPullParser.START_TAG) {
                            when (parser.name) {
                                "key" -> {
                                    var kEvent = parser.next()
                                    while (kEvent != XmlPullParser.END_DOCUMENT) {
                                        if (kEvent == XmlPullParser.END_TAG && parser.name == "key") break
                                        if (kEvent == XmlPullParser.START_TAG && parser.name == "string") {
                                            who = parser.readSimpleText()
                                            parser.next()
                                        }
                                        kEvent = parser.next()
                                    }
                                }
                                "value" -> {
                                    var vEvent = parser.next()
                                    while (vEvent != XmlPullParser.END_DOCUMENT) {
                                        if (vEvent == XmlPullParser.END_TAG && parser.name == "value") break
                                        if (vEvent == XmlPullParser.START_TAG) {
                                            when (parser.name) {
                                                "Points" -> points = parser.readSimpleText().toIntOrNull() ?: 0
                                                "Status" -> status = parser.readSimpleText()
                                                "Friendship" -> { /* advance */ }
                                                else -> parser.skip()
                                            }
                                        }
                                        vEvent = parser.next()
                                    }
                                }
                                else -> parser.skip()                            }
                        }
                        itemEvent = parser.next()
                    }
                    if (who.isNotEmpty()) map[who] = FriendshipData(points, status)
                } else {
                    parser.skip()
                }
                eventType = parser.next()
            } else {
                eventType = parser.next()
            }
        }
        return map
    }
}