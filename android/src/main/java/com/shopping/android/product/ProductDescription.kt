package com.shopping.android.product

import androidx.compose.foundation.Text
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ProductDescription(description: String, modifier: Modifier = Modifier) {
    Text(
        text = description,
        color = Color.DarkGray,
        fontSize = 18.sp,
        modifier = modifier
            .verticalScroll(rememberScrollState()),
    )
}