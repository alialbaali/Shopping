package com.shopping.android.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.*
import androidx.ui.tooling.preview.Preview
import com.shopping.android.ui.DefaultTheme

enum class BottomNavigationItems {
    Products, Cart, Customer
}

@Composable
fun Main() {

    var itemSelection by remember { mutableStateOf(BottomNavigationItems.Cart) }

    BottomNavigation {

        BottomNavigationItems.values().forEach { item ->

            BottomNavigationItem(
                icon = {},
                selected = itemSelection == item,
                onClick = { itemSelection = item }
            )
        }

    }

}
//
//@Composable
//@Preview
//private fun BottomNavigationPreview() {
//    DefaultTheme {
//        Main()
//    }
//}