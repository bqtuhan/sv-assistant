package io.github.bqtuhan.svassistant.data.model

data class VillagerGifts(
    val name: String,
    val loved: List<String>,
    val liked: List<String>
)

data class FishData(
    val id: String,
    val name: String,
    val location: String,
    val time: String,
    val season: String,
    val weather: String
)