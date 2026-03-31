package com.example.ml4.ui.theme

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
    primary = SeaPrimaryDark,
    onPrimary = SeaOnPrimaryDark,
    primaryContainer = SeaPrimaryContainerDark,
    onPrimaryContainer = SeaOnPrimaryContainerDark,
    background = SeaBackgroundDark,
    surface = SeaSurfaceDark,
    surfaceVariant = SeaVariantDark,
    onSurfaceVariant = SeaOnVariantDark
)

private val LightColorScheme = lightColorScheme(
    primary = SeaPrimary,
    onPrimary = SeaOnPrimary,
    primaryContainer = SeaPrimaryContainer,
    onPrimaryContainer = SeaOnPrimaryContainer,
    background = SeaBackground,
    surface = SeaSurface,
    surfaceVariant = SeaVariant,
)

@Composable
fun Ml4Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}