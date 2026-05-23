package io.github.bqtuhan.svassistant.core.ai

import io.github.bqtuhan.svassistant.core.storage.AssetRepository
import io.github.bqtuhan.svassistant.data.model.SaveGame
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RagContextGenerator @Inject constructor(
    private val assetRepository: AssetRepository
) {
    suspend fun generateContext(saveGame: SaveGame): String {
        val mainPlayer = saveGame.players.firstOrNull { it.isMainPlayer } ?: saveGame.players.firstOrNull() ?: return "No save data."
        val gifts = assetRepository.getVillagerGifts()
        val fish = assetRepository.getFishData()

        val sb = StringBuilder()
        sb.appendLine("Current Date: ${saveGame.currentSeason} ${saveGame.dayOfMonth}, Year ${saveGame.year}")
        sb.appendLine("Farm: ${saveGame.farmName}")
        sb.appendLine("Farmer: ${mainPlayer.name}")
        sb.appendLine("Gold Earned: ${mainPlayer.totalMoneyEarned}g")
        sb.appendLine("Deepest Mine Level: ${mainPlayer.deepestMineLevel}")
        
        sb.appendLine("\nLocal Knowledge Base (Gifts):")
        gifts.take(5).forEach {
            sb.appendLine("- ${it.name} loves: ${it.loved.take(3).joinToString()}")
        }
        
        sb.appendLine("\nLocal Knowledge Base (Fish):")
        fish.filter { it.season == saveGame.currentSeason || it.season == "All" }.take(5).forEach {
            sb.appendLine("- ${it.name} can be caught in ${it.location} during ${it.season} (${it.weather})")
        }

        return sb.toString()
    }
}