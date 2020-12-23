package com.shopping.android.cart

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview
import com.shopping.android.components.BottomSheet
import com.shopping.android.ui.DefaultTheme
import com.shopping.common.DummyData
import com.shopping.common.Strings
import com.shopping.common.domain.model.Product
import com.shopping.common.domain.model.valueObject.CartItem


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartScreen(sheetState: ModalBottomSheetState, content: @Composable () -> Unit) {

    val cart = DummyData.customer.cart

    BottomSheet(
        sheetContent = {

            LazyColumnFor(items = listOf(
                CartItem("Samsung", 1),
                CartItem("Samsung", 1),
                CartItem("Samsung", 1),
                CartItem("Samsung", 1),
                CartItem("Samsung", 1),
                CartItem("Samsung", 1),
                CartItem("Samsung", 1),
            )) { cartItem ->
                CartItem(
                    cartItem,
                    Modifier
                        .fillParentMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 12.dp)
                        .padding(12.dp)
                )
            }

        },
        sheetState = sheetState,
        title = Strings.Cart,
        content = content,
    )

}


@Composable
private fun CartItem(cartItem: CartItem, modifier: Modifier = Modifier) {

    val product = Product(
        "",
        Product.Category.Books,
        "Samsung",
        "Galaxy Note 10",
        "A smartphone",
        999.0,
    )

    ConstraintLayout(modifier) {

        val (
            brandAndName,
            image,
            price,
            quantity,
        ) = createRefs()

        createVerticalChain(
            brandAndName, price,
            chainStyle = ChainStyle.SpreadInside
        )

        createHorizontalChain(
            price, quantity,
            chainStyle = ChainStyle.SpreadInside
        )

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colors.primary, MaterialTheme.shapes.medium)
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Column(
            Modifier
                .fillMaxWidth()
                .constrainAs(brandAndName) {
                    start.linkTo(image.end, 8.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(quantity.top, 8.dp)
                },
        ) {

            Text(
                text = product.brand,
                fontSize = 16.sp,
                color = Color.Black.copy(0.5F),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = product.name,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }

        Text(
            text = "$${product.price.times(cartItem.quantity)}",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .constrainAs(price) {
                    top.linkTo(brandAndName.bottom, 4.dp)
                    start.linkTo(image.end, 8.dp)
                    bottom.linkTo(parent.bottom, 4.dp)
                    end.linkTo(quantity.start, 4.dp)
                }
        )

        Quantity(
            cartItem.quantity,
            modifier = Modifier
                .constrainAs(quantity) {
                    start.linkTo(price.end, 4.dp)
                    top.linkTo(brandAndName.bottom, 4.dp)
                    end.linkTo(parent.end, 4.dp)
                    bottom.linkTo(parent.bottom, 4.dp)
                }
        )

    }


}

@Composable
private fun Quantity(quantity: Long, modifier: Modifier = Modifier) {

    var quantityState by remember { mutableStateOf(quantity) }

    Row(
        modifier = modifier
            .background(MaterialTheme.colors.primary.copy(0.15F), CircleShape),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        IconButton(onClick = { quantityState += 1 }) {
            Icon(
                asset = Icons.Outlined.Add,
            )
        }
        Text(text = quantityState.toString())
        IconButton(onClick = { quantityState -= 1 }) {
            Icon(
                asset = Icons.Outlined.Remove,
            )
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Preview(device = Devices.PIXEL_4_XL)
@Composable
fun CartScreenPreview() {
    DefaultTheme {
        CartScreen(rememberModalBottomSheetState(ModalBottomSheetValue.HalfExpanded), emptyContent())
    }
}