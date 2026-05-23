package io.github.bqtuhan.svassistant.core.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import io.github.bqtuhan.svassistant.R

class SVAssistantWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val dataStore = remember { WidgetDataStore(context.applicationContext) }
        val widgetData by dataStore.widgetDataFlow.collectAsState(initial = WidgetData("Spring", 1, 1, "Farm", 0L))

        GlanceTheme {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(R.color.parchment_cream))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = widgetData.farmName,
                    style = TextStyle(color = ColorProvider(R.color.dark_earth), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
                Spacer(modifier = GlanceModifier.height(8.dp))
                Text(
                    text = "${widgetData.season} ${widgetData.day}, Year ${widgetData.year}",
                    style = TextStyle(color = ColorProvider(R.color.wood_brown), fontSize = 16.sp)
                )
                Spacer(modifier = GlanceModifier.height(8.dp))
                Text(
                    text = "${widgetData.gold}g",
                    style = TextStyle(color = ColorProvider(R.color.stardew_gold), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
            }
        }
    }
}