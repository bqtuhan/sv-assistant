package io.github.bqtuhan.svassistant.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.bqtuhan.svassistant.ui.components.PixelDialogBox
import io.github.bqtuhan.svassistant.ui.viewmodel.KnowledgeViewModel

@Composable
fun KnowledgeBaseScreen(viewModel: KnowledgeViewModel = hiltViewModel()) {
    val villagers by viewModel.villagers.collectAsState()
    val fish by viewModel.fish.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Villagers", "Fish")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }
        
        when (selectedTab) {
            0 -> {
                LazyColumn {
                    items(villagers) { v ->
                        PixelDialogBox(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                            Column {
                                Text(v.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                Text("Loved: ${v.loved.joinToString()}", fontSize = 12.sp)
                                Text("Liked: ${v.liked.joinToString()}", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
            1 -> {
                LazyColumn {
                    items(fish) { f ->
                        PixelDialogBox(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                            Column {
                                Text(f.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                Text("Location: ${f.location} (${f.season})", fontSize = 12.sp)
                                Text("Time: ${f.time} | Weather: ${f.weather}", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}