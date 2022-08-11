package com.funnow.core.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFFF5537),
    primaryVariant = Color(0xFFFF5537),
    secondary = Color(0xFF5A69EB),
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color(0xFF252729),
    onBackground = Color(0xFF252729),
    onSurface = Color(0xFF252729),
)

@Composable
fun FunNowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}