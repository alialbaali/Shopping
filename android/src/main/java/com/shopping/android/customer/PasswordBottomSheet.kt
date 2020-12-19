package com.shopping.android.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.shopping.android.components.BottomSheet
import com.shopping.android.components.PasswordTextField
import com.shopping.android.components.PrimaryButton
import com.shopping.android.ui.DefaultTheme
import com.shopping.common.Strings

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChangePasswordBottomSheet(
    sheetState: ModalBottomSheetState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    BottomSheet(
        sheetContent = {

            val itemModifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)

            PasswordTextField(
                value = currentPassword,
                onValueChange = { currentPassword = it },
                modifier = itemModifier,
                placeholder = Strings.CurrentPassword
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                modifier = itemModifier,
                placeholder = Strings.NewPassword
            )

            PrimaryButton(
                text = Strings.Change,
                onClick = {
                    // TODO ViewModel.changePassword(....)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 32.dp)
            )

        },
        modifier = modifier,
        sheetState = sheetState,
        content = content,
        title = Strings.ChangePassword
    )
}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Preview
//@Composable
//private fun PasswordBottomSheetPreview() {
//    DefaultTheme {
//        ChangePasswordBottomSheet(rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded), content = emptyContent())
//    }
//}