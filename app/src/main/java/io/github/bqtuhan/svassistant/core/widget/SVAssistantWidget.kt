package io.github.bqtuhan.svassistant.core.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import io.github.bqtuhan.svassistant.R

class SVAssistantWidget : GlanceAppWidget() {
    
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetContent()
        }
    }

    @Composable
    private fun WidgetContent() {
        val context = LocalContext.current
        val dataStore = remember { WidgetDataStore(context.applicationContext) }
        val widgetData by dataStore.widgetDataFlow.collectAsState(initial = WidgetData("Spring", 1, 1, "Farm", 0L))

        GlanceTheme {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(Color(0xFFF5E6D3), Color(0xFFF5E6D3))) // Parchment Cream
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = widgetData.farmName,
                    style = TextStyle(
                        color = ColorProvider(Color(0xFF5D4037), Color(0xFF5D4037)), // Dark Earth
                        fontWeight = FontWeight.Bold, 
                        fontSize = 20.sp
                    )
                )
                Spacer(modifier = GlanceModifier.height(8.dp))
                Text(
                    text = "${widgetData.season} ${widgetData.day}, Year ${widgetData.year}",
                    style = TextStyle(
                        color = ColorProvider(Color(0xFF8D6E63), Color(0xFF8D6E63)), // Wood Brown
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = GlanceModifier.height(8.dp))
                Text(
                    text = "${widgetData.gold}g",
                    style = TextStyle(
                        color = ColorProvider(Color(0xFFFFD700), Color(0xFFFFD700)), // Stardew Gold
                        fontWeight = FontWeight.Bold, 
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}
