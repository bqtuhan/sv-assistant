package io.github.bqtuhan.svassistant.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bqtuhan.svassistant.core.storage.AssetRepository
import io.github.bqtuhan.svassistant.data.model.FishData
import io.github.bqtuhan.svassistant.data.model.VillagerGifts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KnowledgeViewModel @Inject constructor(
    private val assetRepository: AssetRepository
) : ViewModel() {
    
    private val _villagers = MutableStateFlow<List<VillagerGifts>>(emptyList())
    val villagers = _villagers.asStateFlow()

    private val _fish = MutableStateFlow<List<FishData>>(emptyList())
    val fish = _fish.asStateFlow()

    init {
        viewModelScope.launch {
            _villagers.value = assetRepository.getVillagerGifts()
            _fish.value = assetRepository.getFishData()
        }
    }
}