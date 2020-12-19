package com.shopping.android.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.shopping.android.components.BottomSheet
import com.shopping.android.components.DefaultTextField
import com.shopping.android.components.PrimaryButton
import com.shopping.android.ui.DefaultTheme
import com.shopping.common.Strings

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewCardBottomSheet(sheetState: ModalBottomSheetState, content: @Composable () -> Unit) {

    var number by remember { mutableStateOf("") }
    var cvc by remember { mutableStateOf("") }

    BottomSheet(
        sheetContent = {

            val itemModifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()

            DefaultTextField(
                value = number,
                onValueChange = {
                    number = if (it.length % 4 == 0) it.plus(" ") else it
                },
                placeholder = Strings.Number,
                keyboardType = KeyboardType.Number,
                modifier = itemModifier,
                valueValidator = ::cardNumberValidator
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                itemModifier,
                verticalAlignment = Alignment.CenterVertically
            ) {

                DefaultTextField(
                    value = cvc,
                    onValueChange = { cvc = it },
                    keyboardType = KeyboardType.Number,
                    placeholder = Strings.ExpirationDate,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1F),
                    valueValidator = ::cardCvcValidator
                )

                Spacer(modifier = Modifier.width(12.dp))

                DefaultTextField(
                    value = cvc,
                    onValueChange = { cvc = it },
                    keyboardType = KeyboardType.Number,
                    placeholder = Strings.Cvc,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1F),
                    valueValidator = ::cardCvcValidator
                )
            }

            PrimaryButton(
                text = Strings.Create,
                onClick = {},
                Modifier
                    .padding(horizontal = 16.dp, vertical = 32.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

        },
        sheetState = sheetState,
        title = Strings.CreateCard,
        content = content
    )
}

private fun cardNumberValidator(number: String): Boolean = number.length in 0..19
private fun cardCvcValidator(cvc: String): Boolean = cvc.length in 0..4

//@OptIn(ExperimentalMaterialApi::class)
//@Preview
//@Composable
//private fun NewCardBottomSheetPreview() {
//    DefaultTheme {
//        NewCardBottomSheet(rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded), emptyContent())
//    }
//}