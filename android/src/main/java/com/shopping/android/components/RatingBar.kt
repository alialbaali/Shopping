package com.shopping.android.components

import androidx.compose.foundation.AmbientContentColor
import androidx.compose.material.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    numberOfSelected: Int,
    numberOfNotSelected: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(backgroundColor),
) {
    Surface(modifier = modifier, color = backgroundColor, contentColor = contentColor) {
        Box {
            repeat(numberOfSelected) {
                RatingIcon(selected = true)
            }
            repeat(numberOfNotSelected) {
                RatingIcon(selected = false)
            }
        }
    }
}

@Composable
private fun RatingIcon(
    selected: Boolean,
    icon: VectorAsset = Icons.Outlined.Star,
    modifier: Modifier = Modifier,
    selectedContentColor: Color = AmbientContentColor.current,
    unselectedContentColor: Color = AmbientEmphasisLevels.current.medium.applyEmphasis(
        selectedContentColor
    ),
) {
    Icon(
        icon,
        modifier
            .size(24.dp)
            .padding(2.dp),
        if (selected) selectedContentColor else unselectedContentColor
    )
}
