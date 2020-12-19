package com.shopping.android.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.shopping.android.components.BottomSheet
import com.shopping.android.components.DefaultTextField
import com.shopping.android.components.PrimaryButton
import com.shopping.android.ui.DefaultTheme
import com.shopping.common.Strings

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateAddressBottomSheet(sheetState: ModalBottomSheetState, content: @Composable () -> Unit) {

    var name by remember {mutableStateOf("") }
    var country by remember {mutableStateOf("") }
    var city by remember {mutableStateOf("") }
    var line by remember {mutableStateOf("") }
    var zipCode by remember {mutableStateOf("") }

    BottomSheet(
        sheetContent = {

            val itemModifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()

            DefaultTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = Strings.Name,
                modifier = itemModifier
            )

            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                value = country,
                onValueChange = { country = it },
                placeholder = Strings.Country,
                modifier = itemModifier
            )

            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                value = city,
                onValueChange = { city = it },
                placeholder = Strings.City,
                modifier = itemModifier
            )

            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                value = line,
                onValueChange = { line = it },
                placeholder = Strings.Line,
                modifier = itemModifier
            )

            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                value = zipCode,
                onValueChange = { zipCode = it },
                placeholder = Strings.ZipCode,
                modifier = itemModifier
            )

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
        title = Strings.CreateAddress,
        content = content
    )

}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//@Preview
//private fun CreateAddressBottomSheetPreview() {
//    DefaultTheme {
//        CreateAddressBottomSheet(rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded), emptyContent())
//    }
//}