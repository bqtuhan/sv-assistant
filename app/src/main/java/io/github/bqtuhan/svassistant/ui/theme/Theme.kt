package io.github.bqtuhan.svassistant.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = StardewGold,
    secondary = WoodBrown,
    tertiary = ParchmentCream,
    background = DarkEarth,
    surface = DarkEarth,
    onPrimary = PixelShadow,
    onSecondary = ParchmentCream,
    onBackground = ParchmentCream,
    onSurface = ParchmentCream
)

private val LightColorScheme = lightColorScheme(
    primary = WoodBrown,
    secondary = StardewGold,
    tertiary = DarkEarth,
    background = ParchmentCream,
    surface = ParchmentCream,
    onPrimary = ParchmentCream,
    onSecondary = PixelShadow,
    onBackground = PixelShadow,
    onSurface = PixelShadow
)

@Composable
fun SVAssistantTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}