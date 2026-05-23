package io.github.bqtuhan.svassistant.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bqtuhan.svassistant.core.ai.AiRepository
import io.github.bqtuhan.svassistant.core.ai.RagContextGenerator
import io.github.bqtuhan.svassistant.core.storage.StorageRepository
import io.github.bqtuhan.svassistant.data.model.SaveGameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiViewModel @Inject constructor(
    private val aiRepository: AiRepository,
    private val ragContextGenerator: RagContextGenerator,
    private val storageRepository: StorageRepository
) : ViewModel() {

    private val _advice = MutableStateFlow("Ask the Wizard for daily advice...")
    val advice = _advice.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun requestAdvice() {
        viewModelScope.launch {
            _isLoading.value = true
            val state = storageRepository.saveGameState.value
            if (state is SaveGameState.Success) {
                val context = ragContextGenerator.generateContext(state.saveGame)
                _advice.value = aiRepository.generateAdvice(context)
            } else {
                _advice.value = "Load a save file first to get personalized advice!"
            }
            _isLoading.value = false
        }
    }
}