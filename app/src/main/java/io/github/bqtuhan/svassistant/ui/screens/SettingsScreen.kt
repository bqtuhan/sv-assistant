package io.github.bqtuhan.svassistant.ui.screens

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.bqtuhan.svassistant.data.model.SaveGameState
import io.github.bqtuhan.svassistant.ui.components.PixelDialogBox
import io.github.bqtuhan.svassistant.ui.components.PixelTitle
import io.github.bqtuhan.svassistant.ui.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state by viewModel.saveGameState.collectAsState()
    val context = LocalContext.current
    
    var apiKey by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        apiKey = viewModel.getApiKey()
    }
    
    val safLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            context.contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            viewModel.loadViaSaf(uri)
        }
    }
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
        PixelTitle("Settings & Storage")
        Spacer(Modifier.height(16.dp))
        
        PixelDialogBox(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text("Save File Management", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Button(onClick = { safLauncher.launch(arrayOf("application/xml", "text/xml", "*/*")) }) {
                    Text("Load Save via SAF Picker")
                }
                Spacer(Modifier.height(8.dp))
                Text("Status: ${when(state) {
                    is SaveGameState.Idle -> "No file loaded"
                    is SaveGameState.Loading -> "Parsing XML Stream..."
                    is SaveGameState.Success -> "Loaded Successfully!"
                    is SaveGameState.Error -> "Error: ${(state as SaveGameState.Error).message}"
                }}")
            }
        }
        
        Spacer(Modifier.height(24.dp))
        
        PixelDialogBox(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text("BYOK AI Configuration", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("Enter your Gemini API Key to enable offline RAG AI advice.")
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = apiKey,
                    onValueChange = { 
                        apiKey = it
                        viewModel.saveApiKey(it)
                    },
                    label = { Text("Gemini API Key") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}