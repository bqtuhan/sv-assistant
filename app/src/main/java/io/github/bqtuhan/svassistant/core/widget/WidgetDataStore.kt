package io.github.bqtuhan.svassistant.core.widget

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import io.github.bqtuhan.svassistant.data.model.SaveGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "widget_prefs")

class WidgetDataStore(private val context: Context) {

    companion object {
        val KEY_SEASON = stringPreferencesKey("season")
        val KEY_DAY = intPreferencesKey("day")
        val KEY_YEAR = intPreferencesKey("year")
        val KEY_FARM_NAME = stringPreferencesKey("farm_name")
        val KEY_GOLD = longPreferencesKey("gold")
    }

    suspend fun updateWidgetData(saveGame: SaveGame) {
        val mainPlayer = saveGame.players.firstOrNull { it.isMainPlayer } ?: saveGame.players.firstOrNull()
        context.dataStore.edit { prefs ->
            prefs[KEY_SEASON] = saveGame.currentSeason
            prefs[KEY_DAY] = saveGame.dayOfMonth
            prefs[KEY_YEAR] = saveGame.year
            prefs[KEY_FARM_NAME] = saveGame.farmName
            prefs[KEY_GOLD] = mainPlayer?.totalMoneyEarned ?: 0L
        }
    }
    
    val widgetDataFlow: Flow<WidgetData> = context.dataStore.data.map { prefs ->
        WidgetData(
            season = prefs[KEY_SEASON] ?: "Spring",
            day = prefs[KEY_DAY] ?: 1,
            year = prefs[KEY_YEAR] ?: 1,
            farmName = prefs[KEY_FARM_NAME] ?: "Farm",
            gold = prefs[KEY_GOLD] ?: 0L
        )
    }
}

data class WidgetData(
    val season: String,
    val day: Int,
    val year: Int,
    val farmName: String,
    val gold: Long
)