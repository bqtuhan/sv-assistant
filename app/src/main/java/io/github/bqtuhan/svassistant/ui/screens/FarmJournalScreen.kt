package io.github.bqtuhan.svassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.bqtuhan.svassistant.data.model.SaveGameState
import io.github.bqtuhan.svassistant.ui.components.PixelDialogBox
import io.github.bqtuhan.svassistant.ui.theme.DarkEarth
import io.github.bqtuhan.svassistant.ui.theme.ParchmentCream
import io.github.bqtuhan.svassistant.ui.theme.WoodBrown
import io.github.bqtuhan.svassistant.ui.viewmodel.JournalViewModel

@Composable
fun FarmJournalScreen(viewModel: JournalViewModel = hiltViewModel()) {
    val state by viewModel.saveGameState.collectAsState()
    
    if (state is SaveGameState.Success) {
        val save = (state as SaveGameState.Success).saveGame
        val mainPlayer = save.players.firstOrNull { it.isMainPlayer } ?: save.players.firstOrNull()
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ParchmentCream)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "THE PELICAN TOWN TIMES",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                color = DarkEarth
            )
            Text(
                text = "${save.currentSeason} ${save.dayOfMonth}, Year ${save.year}",
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
                color = WoodBrown
            )
            Spacer(Modifier.height(24.dp))
            
            if (mainPlayer != null) {
                PixelDialogBox(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text("FARMER ${mainPlayer.name.uppercase()} CHRONICLES", fontWeight = FontWeight.Bold, color = DarkEarth)
                        Spacer(Modifier.height(8.dp))
                        JournalEntry("Trash Cans Checked", mainPlayer.stats["trashCansChecked"] ?: "0")
                        JournalEntry("Times Passed Out", mainPlayer.stats["timesPassedOut"] ?: "0")
                        JournalEntry("Pieces of Trash Caught", mainPlayer.stats["piecesOfTrashCaught"] ?: "0")
                        JournalEntry("Steps Taken", mainPlayer.stats["stepsTaken"] ?: "0")
                        JournalEntry("Fish Caught", mainPlayer.stats["fishCaught"] ?: "0")
                    }
                }
            }
        }
    } else {
        Box(Modifier.fillMaxSize().background(ParchmentCream), contentAlignment = Alignment.Center) {
            Text("Load a save file to read the Journal.", color = DarkEarth)
        }
    }
}

@Composable
fun JournalEntry(title: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, fontFamily = FontFamily.Monospace, color = DarkEarth)
        Text(text = value, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, color = WoodBrown)
    }
}