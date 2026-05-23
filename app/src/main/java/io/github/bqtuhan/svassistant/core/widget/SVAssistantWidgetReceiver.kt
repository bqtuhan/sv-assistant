package io.github.bqtuhan.svassistant.core.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class SVAssistantWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = SVAssistantWidget()
    
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }
}