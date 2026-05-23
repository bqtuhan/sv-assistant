package io.github.bqtuhan.svassistant.core.storage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.bqtuhan.svassistant.data.model.FishData
import io.github.bqtuhan.svassistant.data.model.VillagerGifts
import io.github.bqtuhan.svassistant.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONArray
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssetRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getVillagerGifts(): List<VillagerGifts> = withContext(ioDispatcher) {
        val list = mutableListOf<VillagerGifts>()
        try {
            val jsonString = context.assets.open("villager_gifts.json").bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val loved = mutableListOf<String>()
                val liked = mutableListOf<String>()
                
                val lovedArray = obj.optJSONArray("loved")
                if (lovedArray != null) {
                    for (j in 0 until lovedArray.length()) loved.add(lovedArray.optString(j))
                }
                
                val likedArray = obj.optJSONArray("liked")
                if (likedArray != null) {
                    for (j in 0 until likedArray.length()) liked.add(likedArray.optString(j))
                }
                
                list.add(VillagerGifts(obj.optString("name"), loved, liked))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        list
    }

    suspend fun getFishData(): List<FishData> = withContext(ioDispatcher) {
        val list = mutableListOf<FishData>()
        try {
            val jsonString = context.assets.open("fish_data.json").bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                list.add(FishData(
                    id = obj.optString("id"),
                    name = obj.optString("name"),
                    location = obj.optString("location"),
                    time = obj.optString("time"),
                    season = obj.optString("season"),
                    weather = obj.optString("weather")
                ))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        list
    }
}