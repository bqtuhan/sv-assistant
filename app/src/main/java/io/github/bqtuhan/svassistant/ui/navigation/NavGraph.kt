package io.github.bqtuhan.svassistant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.bqtuhan.svassistant.ui.screens.DashboardScreen
import io.github.bqtuhan.svassistant.ui.screens.FarmJournalScreen
import io.github.bqtuhan.svassistant.ui.screens.KnowledgeBaseScreen
import io.github.bqtuhan.svassistant.ui.screens.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {
        composable(Screen.Dashboard.route) { DashboardScreen() }
        composable(Screen.Journal.route) { FarmJournalScreen() }
        composable(Screen.Knowledge.route) { KnowledgeBaseScreen() }
        composable(Screen.Settings.route) { SettingsScreen() }
    }
}