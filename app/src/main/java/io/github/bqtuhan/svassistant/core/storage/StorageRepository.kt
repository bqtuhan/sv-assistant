package io.github.bqtuhan.svassistant.core.storage

import android.content.Context
import android.net.Uri
import androidx.glance.appwidget.updateAll
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.bqtuhan.svassistant.core.parser.SaveParser
import io.github.bqtuhan.svassistant.core.shizuku.ShizukuManager
import io.github.bqtuhan.svassistant.core.widget.SVAssistantWidget
import io.github.bqtuhan.svassistant.core.widget.WidgetDataStore
import io.github.bqtuhan.svassistant.data.model.SaveGameState
import io.github.bqtuhan.svassistant.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val shizukuManager: ShizukuManager,
    private val saveParser: SaveParser,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    private val _saveGameState = MutableStateFlow<SaveGameState>(SaveGameState.Idle)
    val saveGameState: StateFlow<SaveGameState> = _saveGameState.asStateFlow()
    
    private val widgetDataStore = WidgetDataStore(context)

    suspend fun loadViaShizuku(path: String) {
        _saveGameState.value = SaveGameState.Loading
        withContext(ioDispatcher) {
            try {
                if (!shizukuManager.checkPermission()) {
                    _saveGameState.value = SaveGameState.Error("Shizuku permission denied.")
                    return@withContext
                }
                if (!shizukuManager.bindService()) {
                    _saveGameState.value = SaveGameState.Error("Failed to bind Shizuku service.")
                    return@withContext
                }
                val inputStream = shizukuManager.getSaveInputStream(path)
                if (inputStream == null) {
                    _saveGameState.value = SaveGameState.Error("Save file is empty or inaccessible.")
                    return@withContext
                }
                inputStream.use { stream ->
                    val saveGame = saveParser.parse(stream)
                    widgetDataStore.updateWidgetData(saveGame)
                    SVAssistantWidget().updateAll(context)
                    _saveGameState.value = SaveGameState.Success(saveGame)
                }
            } catch (e: Exception) {
                _saveGameState.value = SaveGameState.Error("Parse error: ${e.message}")
            }
        }
    }

    suspend fun loadViaSaf(uri: Uri) {
        _saveGameState.value = SaveGameState.Loading
        withContext(ioDispatcher) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                if (inputStream == null) {
                    _saveGameState.value = SaveGameState.Error("Failed to read file via SAF.")
                    return@withContext
                }
                inputStream.use { stream ->
                    val saveGame = saveParser.parse(stream)
                    widgetDataStore.updateWidgetData(saveGame)
                    SVAssistantWidget().updateAll(context)
                    _saveGameState.value = SaveGameState.Success(saveGame)
                }
            } catch (e: Exception) {
                _saveGameState.value = SaveGameState.Error("Parse error: ${e.message}")
            }
        }
    }
    
    fun clearState() {
        _saveGameState.value = SaveGameState.Idle
    }
}