package com.shopping.android.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.border
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick,
        modifier
    ) {
        Text(
            text,
            color = MaterialTheme.colors.primaryVariant,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        )
    }
}