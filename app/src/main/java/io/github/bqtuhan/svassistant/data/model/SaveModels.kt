package io.github.bqtuhan.svassistant.data.model

data class SaveGame(
    val farmName: String,
    val gameVersion: String,
    val currentSeason: String,
    val dayOfMonth: Int,
    val year: Int,
    val millisecondsPlayed: Long,
    val players: List<PlayerData>
)

data class PlayerData(
    val name: String,
    val uniqueMultiplayerId: String,
    val isMainPlayer: Boolean,
    val totalMoneyEarned: Long,
    val houseUpgradeLevel: Int,
    val spouse: String?,
    val maxStamina: Int,
    val deepestMineLevel: Int,
    val experiencePoints: List<Int>,
    val stats: Map<String, String>,
    val mailReceived: Set<String>,
    val eventsSeen: Set<String>,
    val friendshipData: Map<String, FriendshipData>
)

data class FriendshipData(
    val points: Int,
    val status: String
)

sealed class SaveGameState {
    data object Idle : SaveGameState()
    data object Loading : SaveGameState()
    data class Success(val saveGame: SaveGame) : SaveGameState()
    data class Error(val message: String) : SaveGameState()
}