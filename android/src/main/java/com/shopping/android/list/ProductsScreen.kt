package com.shopping.android.list

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabConstants.defaultTabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shopping.android.components.RatingBar
import com.shopping.android.components.SearchTextField
import com.shopping.common.domain.model.Product
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ProductsScreen(navController: NavController) {

//    val vm = ServiceLocator.ViewModel.productViewModel

    val scope = rememberCoroutineScope()
//    scope.launch { vm.getProducts() }

    var search by mutableStateOf("")

    var selectedTab by remember { mutableStateOf(Product.Category.Movies) }

    Column(
        Modifier
            .background(Color.White)
            .padding(20.dp),
    ) {

        Text(
            text = "Welcome back Ali Albaali", fontSize = 36.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        SearchTextField(
            value = search,
            onValueChange = { search = it },
        )

        Text(
            text = "Categories",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        CategoriesTabRow(selectedTab = selectedTab, onTabSelect = { selectedTab = it })


        LazyColumnFor(
            items = emptyList<Product>(),
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) { product ->

            Row {
                ProductItem(product)
                Spacer(
                    Modifier
                        .width(16.dp)
                )
                ProductItem(product)
            }
        }

    }
}


@Composable
private fun CategoriesTabRow(
    selectedTab: Product.Category,
    onTabSelect: (Product.Category) -> Unit
) {
    ScrollableTabRow(
        selectedTab.ordinal,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        edgePadding = 0.dp,
        divider = emptyContent(),
        indicator = { tabPositions ->
            Box(
                Modifier
                    .padding(bottom = 2.dp)
                    .defaultTabIndicatorOffset(tabPositions[selectedTab.ordinal])
                    .background(Color.Black, CircleShape)
                    .size(6.dp)
            )
        },
    ) {
        Product.Category.values().forEach { category ->
            CategoryTab(
                category = category,
                selected = selectedTab == category,
                onClick = { onTabSelect(category) }
            )
        }
    }
}

@Composable
private fun CategoryTab(category: Product.Category, selected: Boolean, onClick: () -> Unit) {
    Tab(
        selected = selected,
        onClick = onClick,
        text = {
            if (selected)
                Text(
                    text = category.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            else
                Text(
                    text = category.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(0.5F),
                )
        }
    )
}

@Composable
private fun ProductItem(product: Product, modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .wrapContentSize()
            .drawShadow(2.dp, RoundedCornerShape(20.dp))
            .padding(8.dp)
    ) {

        CoilImage(
            data = "https://static.nike.com/a/images/t_PDP_864_v1/f_aut" +
                    "o,b_rgb:f5f5f5/i1-68df1a28-0740-48a2-97c0-092b84cbfc61/" +
                    "jordan-aerospace-720-mens-shoe-dBclR3.jpg",
            loading = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    LinearProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        Text(
            text = product.brand,
            fontSize = 16.sp,
            color = Color.Black.copy(alpha = 0.5F)
        )
        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        Text(
            text = product.name,
            fontSize = 20.sp
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        RatingBar(numberOfSelected = (1 until 5).random(), numberOfNotSelected = (1 until 5).random())

        Text(
            text = "$${product.price}",
            fontSize = 16.sp
        )


    }
}

//@Preview(device = Devices.PIXEL_4_XL)
//@Composable
//fun ProductScreenPreview() {
//    MaterialTheme {
//        ProductsScreen()
//    }
//}