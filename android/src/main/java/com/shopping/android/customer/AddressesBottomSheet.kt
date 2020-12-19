package com.shopping.android.customer

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.shopping.android.DefaultIcons
import com.shopping.android.components.BottomSheet
import com.shopping.android.ui.DefaultTheme
import com.shopping.android.ui.typography
import com.shopping.common.DummyData
import com.shopping.common.Strings
import com.shopping.common.domain.model.valueObject.Address

@ExperimentalMaterialApi
@Composable
fun AddressesBottomSheet(sheetState: ModalBottomSheetState, content: @Composable () -> Unit) {

    val addresses = DummyData.customer.addresses.toList()

    BottomSheet(
        sheetContent = {
            LazyRowFor(items = addresses) { address ->
                AddressItem(address)
            }
        },
        sheetState = sheetState,
        title = Strings.Addresses,
        content = content,
        action = {
            Icon(
                asset = DefaultIcons.Add,
                tint = MaterialTheme.colors.onBackground.copy(0.5F),
                modifier = Modifier
                    .clickable {

                    }
            )
        },
    )

}

@Composable
private fun AddressItem(address: Address, modifier: Modifier = Modifier) {

    ConstraintLayout(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .widthIn(max = 200.dp)
            .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {

        val (name, countryAndCity, line, zipCode) = createRefs()

        createVerticalChain(
            name, countryAndCity, line,
            chainStyle = ChainStyle.SpreadInside
        )

        createHorizontalChain(
            name, zipCode,
            chainStyle = ChainStyle.SpreadInside,
        )

        Text(
            text = address.name,
            Modifier
                .constrainAs(name) {
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(zipCode.start, 8.dp)
                    bottom.linkTo(countryAndCity.top, 8.dp)
                }
        )
        Row(
            modifier = Modifier
                .constrainAs(countryAndCity) {
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(name.bottom, 8.dp)
                    bottom.linkTo(line.top, 8.dp)
                    end.linkTo(zipCode.start, 8.dp)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = annotatedString {
                    append(address.city)
                    pushStyle(SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = typography.h6.fontSize))
                    append(" / ")
                    pop()
                    append(address.country)
                }
            )
        }
        Text(
            text = address.line,
            modifier = Modifier
                .constrainAs(line) {
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(countryAndCity.bottom, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                }
        )
        Text(
            text = address.zipCode,
            modifier = Modifier
                .constrainAs(zipCode) {
                    start.linkTo(name.end, 8.dp)
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                }
        )
    }
}


//@ExperimentalMaterialApi
//@Preview
//@Composable
//private fun AddressesBottomSheetPreview() {
//    DefaultTheme {
//        AddressesBottomSheet(rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded), content = emptyContent())
//    }
//}