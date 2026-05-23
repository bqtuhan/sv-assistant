package io.github.bqtuhan.svassistant.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Default.Home)
    object Journal : Screen("journal", "Journal", Icons.Default.DateRange)
    object Knowledge : Screen("knowledge", "Knowledge", Icons.Default.Search)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}