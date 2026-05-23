package io.github.bqtuhan.svassistant.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.bqtuhan.svassistant.data.model.SaveGameState
import io.github.bqtuhan.svassistant.ui.components.PixelDialogBox
import io.github.bqtuhan.svassistant.ui.components.PixelTitle
import io.github.bqtuhan.svassistant.ui.viewmodel.AiViewModel
import io.github.bqtuhan.svassistant.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    aiViewModel: AiViewModel = hiltViewModel()
) {
    val state by dashboardViewModel.saveGameState.collectAsState()
    val advice by aiViewModel.advice.collectAsState()
    val isLoading by aiViewModel.isLoading.collectAsState()
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
        PixelTitle("Dashboard")
        Spacer(Modifier.height(16.dp))
        
        PixelDialogBox(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text("Wizard's Daily Advice", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(advice)
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = { aiViewModel.requestAdvice() },
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.height(16.dp).padding(end = 8.dp))
                    }
                    Text("Consult the Spirits")
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        when (val s = state) {
            is SaveGameState.Idle -> {
                Text("Load a save file from Settings to view your farm status.")
            }
            is SaveGameState.Loading -> {
                CircularProgressIndicator()
                Text("Parsing XML Stream...")
            }
            is SaveGameState.Error -> {
                Text("Parse Error: ${s.message}")
            }
            is SaveGameState.Success -> {
                val save = s.saveGame
                val mainPlayer = save.players.firstOrNull { it.isMainPlayer } ?: save.players.firstOrNull()
                
                PixelDialogBox(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text("${save.farmName} Farm", fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        Text("Farmer: ${mainPlayer?.name ?: "Unknown"}")
                        Text("Season: ${save.currentSeason}, Day ${save.dayOfMonth}, Year ${save.year}")
                        Text("Gold Earned: ${mainPlayer?.totalMoneyEarned ?: 0}g")
                        Text("Deepest Mine Level: ${mainPlayer?.deepestMineLevel ?: 0}")
                        Text("Max Stamina: ${mainPlayer?.maxStamina ?: 270}")
                    }
                }
            }
        }
    }
}