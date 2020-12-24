package com.shopping.android.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val teal100 = Color(0xFFB2DFDB)
private val teal300 = Color(0xFF4DB6AC)
private val white100 = Color(0xFFF5F5F5)
private val black800 = Color(0xFF424242)
private val red900 = Color(0xFFB00020)
private val white = Color.White
private val black = Color.Black

private val teal600 = Color(0xFF00897B)
private val teal800 = Color(0xFF00695C)

private val LightColorPalette = lightColors(
    primary = teal100,
    primaryVariant = teal300,
    onPrimary = black,
    surface = white100.copy(0.5F),
    onSurface = black800,
    background = white,
    onBackground = black,
    error = red900,
    onError = white
)


private val DarkColorPalette = darkColors(
    primary = teal800,
    primaryVariant = teal600,
)

@Composable
fun DefaultTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}