package com.shopping.frontend

import kotlinx.css.Color

private val teal100 = Color("#B2DFDB")
private val teal300 = Color("#4DB6AC")
private val white100 = Color("#F5F5F5")
private val red900 = Color("#B00020")
private val black = Color.black
private val white = Color.white

object Colors {

    val primary = teal100
    val primaryVariant = teal300
    val onPrimary = black

    val surface = white100.withAlpha(0.5)
    val onSurface = black

    val background = white
    val onBackground = black

    val error = red900
    val onError = white

}