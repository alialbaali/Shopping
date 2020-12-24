package com.shopping.android.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.shopping.android.ui.DefaultTheme

@Composable
fun BarColumn(
    modifier: Modifier = Modifier,
    height: Dp = 36.dp,
    shape: Shape = CircleShape,
    elevation: Dp = 0.dp,
    @FloatRange(from = 0.0, to = 1.0) fraction: Float = 0.5F,
    backgroundColor: Color = Color.LightGray.copy(0.25F),
    foregroundColor: Color = Color.Blue.copy(green = 0.6F),
    children: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier
            .padding(8.dp),
        Alignment.Center,
    ) {

        children()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(backgroundColor, shape)
        )

        Box(
            modifier = Modifier
                .drawShadow(elevation, shape)
                .fillMaxWidth(fraction)
                .height(height)
                .background(foregroundColor, shape)
                .align(Alignment.CenterStart),
        )
    }
}

@Composable
fun BarRow(
    modifier: Modifier = Modifier,
    width: Dp = 36.dp,
    shape: Shape = CircleShape,
    elevation: Dp = 0.dp,
    @FloatRange(from = 0.0, to = 1.0) fraction: Float = 0.5F,
    backgroundColor: Color = Color.LightGray.copy(0.25F),
    foregroundColor: Color = Color.Blue.copy(green = 0.6F),
    children: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier
            .padding(8.dp),
        Alignment.Center,
    ) {

        children()

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(width)
                .background(backgroundColor, shape)
        )

        Box(
            modifier = Modifier
                .drawShadow(elevation, shape)
                .fillMaxHeight(fraction)
                .width(width)
                .background(foregroundColor, shape)
                .align(Alignment.TopStart),
        )
    }
}


//@Preview
//@Composable
//private fun BarPreview() {
//    DefaultTheme {
//        BarColumn()
//        BarRow()
//    }
//}