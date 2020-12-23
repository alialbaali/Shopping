package com.shopping.android.product

import androidx.compose.animation.animate
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabConstants.defaultTabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview
import com.shopping.android.DefaultIcons
import com.shopping.android.ui.DefaultTheme
import com.shopping.common.DummyData
import com.shopping.common.Strings

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductDetailsScreen(navController: NavController) {

    val product = DummyData.products.random()

    ProductDrawer(
        drawerContent = {

            var selectedTab by remember { mutableStateOf(ProductDetailsTabSelection.Description) }

            ProductTabRow(selectedTab = selectedTab, onTabSelect = { selectedTab = it })

            val swipeableState = rememberSwipeableState(initialValue = selectedTab)

            WithConstraints {

                val maxValue = -constraints.maxWidth.toFloat()
                val midValue = 0F
                val minValue = constraints.maxWidth.toFloat()


                val anchors = mapOf(
                    minValue to ProductDetailsTabSelection.Description,
                    midValue to ProductDetailsTabSelection.Specs,
                    maxValue to ProductDetailsTabSelection.Reviews,
                )

                Box(
                    Modifier
                        .padding(20.dp)
                        .swipeable(
                            swipeableState,
                            anchors = anchors,
                            thresholds = { _, _ -> FractionalThreshold(0.5F) },
                            orientation = Orientation.Horizontal,
                        )
                ) {
                    when (selectedTab) {
                        ProductDetailsTabSelection.Description -> ProductDescription(product.description, Modifier.fillMaxSize())
                        ProductDetailsTabSelection.Specs -> ProductSpecs(product.specs)
                        ProductDetailsTabSelection.Reviews -> ProductReviews(product.reviews)
                    }
                    AddToCartButton {

                    }
                }
            }

        },
        bodyContent = {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceAround,
            ) {

                Icon(
                    asset = Icons.Rounded.ArrowBack,
                    modifier = Modifier
                        .size(30.dp)
                )

                Text(
                    text = product.brand,
                    fontSize = 18.sp,
                    color = Color.Black.copy(0.5F),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                )

                Text(
                    text = product.name,
                    fontSize = 30.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    Modifier
                        .fillMaxHeight(0.25F)
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primary)
                )

                Text(
                    text = "$${product.price}",
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.End
                )
            }

        }
    )
}

@Composable
private fun AddToCartButton(modifier: Modifier = Modifier, onClick: () -> Unit) {

    IconButton(
        onClick,
        modifier
            .drawShadow(4.dp, CircleShape)
            .background(MaterialTheme.colors.primary)
            .clip(MaterialTheme.shapes.medium)
            .padding(10.dp),
    ) {

        Text(text = Strings.AddToCart)

        Icon(
            DefaultIcons.AddShoppingCart,
        )

    }

}
enum class ProductDetailsTabSelection {
    Description, Specs, Reviews
}

@Composable
fun ProductTabRow(
    selectedTab: ProductDetailsTabSelection,
    onTabSelect: (ProductDetailsTabSelection) -> Unit,
    modifier: Modifier = Modifier,
) {

    TabRow(
        selectedTabIndex = selectedTab.ordinal,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top)
            .padding(4.dp),
        backgroundColor = Color.White,
        contentColor = MaterialTheme.colors.primary,
        indicator = { tabPositions ->
            ProductTabsIndicator(
                Modifier
                    .defaultTabIndicatorOffset(tabPositions[selectedTab.ordinal]),
            )
        },
        divider = emptyContent(),
    ) {

        ProductTab(
            selected = selectedTab == ProductDetailsTabSelection.Description,
            onClick = { onTabSelect(ProductDetailsTabSelection.Description) },
            text = "Description",
        )
        ProductTab(
            selected = selectedTab == ProductDetailsTabSelection.Specs,
            onClick = { onTabSelect(ProductDetailsTabSelection.Specs) },
            text = "Specs",
        )
        ProductTab(
            selected = selectedTab == ProductDetailsTabSelection.Reviews,
            onClick = { onTabSelect(ProductDetailsTabSelection.Reviews) },
            text = "Reviews",
        )

    }
}

@Composable
private fun ProductTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
) {
    val colorAnimation =
        animate(if (selected) MaterialTheme.colors.primary else Color.LightGray)

    Tab(
        selected,
        onClick,
        modifier,
        text = {
            Text(
                text = text,
                fontSize = if (selected) 17.sp else 15.sp,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,

                )
        },
        selectedContentColor = colorAnimation,
        unselectedContentColor = colorAnimation
    )
}


@Composable
private fun ProductTabsIndicator(
    modifier: Modifier = Modifier,
    height: Dp = 3.5.dp,
) {
    Box(
        modifier
            .background(MaterialTheme.colors.primary, RoundedCornerShape(topRight = 24.dp, topLeft = 24.dp))
            .fillMaxWidth()
            .preferredHeight(height)
    )
}


@Preview(device = Devices.PIXEL_4_XL)
@Composable
private fun ProductPreview() {
    DefaultTheme {
        ProductDetailsScreen(rememberNavController())
    }
}