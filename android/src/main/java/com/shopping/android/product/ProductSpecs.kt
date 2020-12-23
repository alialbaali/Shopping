package com.shopping.android.product

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ProductSpecs(specs: Map<String, String>, modifier: Modifier = Modifier) {
    Column(modifier) {
        specs.forEach { (key, value) ->
            ProductSpecItem(key, value)
        }
    }
}

@Composable
private fun ProductSpecItem(key: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier) {
        Text(
            text = key,
            modifier = Modifier.align(Alignment.Top),
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            modifier = Modifier,
            fontWeight = FontWeight.Light
        )
    }
}