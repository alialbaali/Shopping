package com.shopping.android.product

import androidx.compose.animation.animate
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DrawerConstants.ScrimDefaultOpacity
import androidx.compose.material.TabConstants.defaultTabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.drawLayer
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview

private enum class ProductDrawerState {
    Open, Expanded
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductDrawer(
    drawerContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    bodyContent: @Composable () -> Unit,
) {
    WithConstraints(modifier.fillMaxSize()) {

        val drawerState = rememberSwipeableState(initialValue = ProductDrawerState.Open)

        val minValue = constraints.maxHeight.toFloat() / 2
        val maxValue = constraints.maxHeight.toFloat() / 8

        val anchors = mapOf(
            minValue to ProductDrawerState.Open,
            maxValue to ProductDrawerState.Expanded,
        )

        Box(
            Modifier
                .background(MaterialTheme.colors.primary)
                .swipeable(
                    state = drawerState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(fraction = 0.5F) },
                    orientation = Orientation.Vertical,
                )
        ) {

            val scaleValue = 1 - calculateFraction(minValue, maxValue, drawerState.offset.value) / 15
            val cornerValue = animate(target = if (drawerState.isExpanded) 20.dp else 0.dp)

            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5F)
                    .drawLayer(scaleValue, scaleValue)
                    .clip(RoundedCornerShape(cornerValue))
            ) {
                bodyContent()
            }

            Scrim(
                isExpanded = drawerState.isExpanded,
                onOpen = { drawerState.snapTo(ProductDrawerState.Open) },
                fraction = { calculateFraction(minValue, maxValue, drawerState.offset.value) / 2 },
                color = MaterialTheme.colors.onSurface.copy(alpha = ScrimDefaultOpacity)
            )

            val drawerContentShape = RoundedCornerShape(topLeft = 20.dp, topRight = 20.dp)

            Box(
                modifier = with(DensityAmbient.current) {
                    Modifier.preferredSizeIn(
                        minWidth = constraints.minWidth.toDp(),
                        minHeight = constraints.minHeight.toDp(),
                        maxWidth = constraints.maxWidth.toDp(),
                        maxHeight = constraints.maxHeight.toDp()
                    )
                }.offsetPx(y = drawerState.offset)
                    .clip(drawerContentShape)
                    .drawShadow(16.dp, drawerContentShape)
                    .background(Color.White)

            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxHeight(fraction = 0.9F)
                        .fillMaxWidth(),
                    children = drawerContent
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private val SwipeableState<ProductDrawerState>.isOpen
    get() = value == ProductDrawerState.Open

@OptIn(ExperimentalMaterialApi::class)
private val SwipeableState<ProductDrawerState>.isExpanded
    get() = value == ProductDrawerState.Expanded


private fun calculateFraction(a: Float, b: Float, pos: Float) = ((pos - a) / (b - a)).coerceIn(0f, 1f)

@Composable
private fun Scrim(
    isExpanded: Boolean,
    onOpen: () -> Unit,
    fraction: () -> Float,
    color: Color,
) {
    val dismissDrawer = if (isExpanded) {
        Modifier.tapGestureFilter { onOpen() }
    } else {
        Modifier
    }

    Canvas(
        Modifier
            .fillMaxSize()
            .then(dismissDrawer)
    ) {
        drawRect(color, alpha = fraction())
    }
}


//
//@Preview(device = Devices.PIXEL_4_XL)
//@Composable
//private fun ProductDrawerPreview() {
//    ProductDrawer(
//        drawerContent = {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colors.onPrimary)
//            )
//        },
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colors.primary)
//        )
//    }
//}