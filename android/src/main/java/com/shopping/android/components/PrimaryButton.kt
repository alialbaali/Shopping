package com.shopping.android.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.shopping.android.ui.DefaultTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp
) {
    Button(
        onClick,
        modifier,
        shape = MaterialTheme.shapes.small,
        elevation = ButtonConstants.defaultElevation(defaultElevation = elevation),
        colors = ButtonConstants.defaultButtonColors(backgroundColor = MaterialTheme.colors.primary)
    ) {
        Text(
            text, color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.W600
        )
    }
}
//
//@Preview(showBackground = true)
//@Composable
//private fun PrimaryButtonPreview() {
//    DefaultTheme {
//        Surface(
//            Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colors.background)
//        ) {
//            PrimaryButton(
//                "Sign Up",
//                {},
//                Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight()
//                    .padding(16.dp)
//            )
//        }
//    }
//}