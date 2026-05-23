package io.github.bqtuhan.svassistant.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.bqtuhan.svassistant.ui.theme.DarkEarth
import io.github.bqtuhan.svassistant.ui.theme.WoodBrown

@Composable
fun PixelDialogBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .border(width = 4.dp, color = WoodBrown)
            .border(width = 2.dp, color = DarkEarth)
            .padding(16.dp)
    ) {
        content()
    }
}

@Composable
fun PixelTitle(text: String) {
    Text(
        text = text,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = MaterialTheme.colorScheme.onSurface
    )
}