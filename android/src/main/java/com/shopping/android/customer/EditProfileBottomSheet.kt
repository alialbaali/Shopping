package com.shopping.android.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.shopping.android.components.BottomSheet
import com.shopping.android.components.EmailTextField
import com.shopping.android.components.NameTextField
import com.shopping.android.components.PrimaryButton
import com.shopping.android.ui.DefaultTheme
import com.shopping.common.DummyData
import com.shopping.common.Strings
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.flow.MutableStateFlow


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditProfileBottomSheet(sheetState: ModalBottomSheetState, content: @Composable () -> Unit) {

    val customer = DummyData.customer
    var name by remember { mutableStateOf(customer.name) }
    var email by remember { mutableStateOf(customer.email.toString()) }

    BottomSheet(
        sheetContent = {

            val itemModifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()

            Box(
//                data = customer.imageUrl,
                modifier = Modifier
                    .drawShadow(4.dp, MaterialTheme.shapes.large)
                    .size(125.dp)
                    .background(MaterialTheme.colors.primary, MaterialTheme.shapes.large)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        // TODO Choose image from gallery
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NameTextField(
                value = name,
                onValueChange = { name = it },
                itemModifier,
            )

            Spacer(modifier = Modifier.height(16.dp))

            EmailTextField(
                value = email,
                onValueChange = { email = it },
                itemModifier,
            )

            PrimaryButton(
                text = Strings.Update,
                onClick = { },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 32.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        },
        sheetState = sheetState,
        title = Strings.EditProfile,
        content = content
    )
}

//@OptIn(ExperimentalMaterialApi::class)
//@Preview
//@Composable
//private fun EditProfileBottomSheetPreview() {
//    DefaultTheme {
//        EditProfileBottomSheet(sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded), content = emptyContent())
//    }
//}
