package com.shopping.android.customer

import androidx.compose.material.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.shopping.android.DefaultIcons
import com.shopping.android.components.BottomSheet
import com.shopping.android.month
import com.shopping.android.ui.DefaultTheme
import com.shopping.android.year
import com.shopping.common.DummyData
import com.shopping.common.Strings
import com.shopping.common.domain.model.valueObject.Card

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardsBottomSheet(sheetState: ModalBottomSheetState, content: @Composable () -> Unit) {

    val cards = DummyData.customer.cards.toList()

    BottomSheet(
        sheetContent = {
            LazyRowFor(items = cards) { card ->
                CardItem(card)
            }
        },
        sheetState = sheetState,
        title = Strings.Cards,
        content = content,
        action = {
            Icon(
                asset = DefaultIcons.Add,
                tint = MaterialTheme.colors.onBackground.copy(0.5F),
                modifier = Modifier
                    .clickable {

                    }
            )
        }
    )
}

@Composable
private fun CardItem(card: Card, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            text = card.brand,
            fontFamily = FontFamily.Monospace,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "**** **** **** ${card.number.toString().takeLast(4)}",
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = annotatedString {
                append(card.month.toString())
                pushStyle(SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
                append(" / ")
                pop()
                append(card.year.toString())
            },
        )

    }
}

//
//@OptIn(ExperimentalMaterialApi::class)
//@Preview
//@Composable
//private fun CardsBottomSheetPreview() {
//    DefaultTheme {
//        CardsBottomSheet(
//            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded),
//            content = emptyContent()
//        )
//    }
//}
//
