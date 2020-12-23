package com.shopping.android

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.VerticalGradient
import com.shopping.common.domain.model.valueObject.Card


fun Modifier.verticalGradient(colors: List<Color>, startY: Float, endY: Float,  tileMode: TileMode = TileMode.Clamp) = background(VerticalGradient(colors, startY, endY, tileMode))

val Card.month
    get() = expirationDate.monthNumber

val Card.year
    get() = expirationDate.year


typealias DefaultIcons = Icons.Outlined