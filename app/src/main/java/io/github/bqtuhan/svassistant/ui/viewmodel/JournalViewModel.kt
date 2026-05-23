package io.github.bqtuhan.svassistant.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bqtuhan.svassistant.core.storage.StorageRepository
import io.github.bqtuhan.svassistant.data.model.SaveGameState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {
    val saveGameState = storageRepository.saveGameState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SaveGameState.Idle
    )
}