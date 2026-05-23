package io.github.bqtuhan.svassistant.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bqtuhan.svassistant.core.security.SecurityManager
import io.github.bqtuhan.svassistant.core.storage.StorageRepository
import io.github.bqtuhan.svassistant.data.model.SaveGameState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val storageRepository: StorageRepository,
    private val securityManager: SecurityManager
) : ViewModel() {
    
    val saveGameState = storageRepository.saveGameState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SaveGameState.Idle
    )

    fun loadViaSaf(uri: Uri) {
        viewModelScope.launch {
            storageRepository.loadViaSaf(uri)
        }
    }
    
    fun saveApiKey(key: String) {
        securityManager.securePrefs.edit().putString("gemini_api_key", key).apply()
    }
    
    fun getApiKey(): String {
        return securityManager.securePrefs.getString("gemini_api_key", "") ?: ""
    }
}