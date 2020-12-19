package com.shopping.android.customer

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.shopping.android.DefaultIcons
import com.shopping.android.components.BottomSheet
import com.shopping.android.ui.DefaultTheme
import com.shopping.common.Strings

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsBottomSheet(
    sheetState: ModalBottomSheetState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = emptyContent(),
) {
    BottomSheet(
        sheetContent = {

            SettingsItem(
                text = Strings.EditProfile,
                asset = DefaultIcons.Person,
            ) {}

            Spacer(modifier = Modifier.height(16.dp))

            SettingsItem(
                text = Strings.ChangePassword,
                asset = DefaultIcons.Lock,
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            SettingsItem(
                text = Strings.SignOut,
                asset = DefaultIcons.ExitToApp,
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            SettingsItem(
                text = Strings.DeleteAccount,
                asset = DefaultIcons.Delete,
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

        },
        modifier = modifier,
        sheetState = sheetState,
        title = Strings.Settings,
        content = content,
    )


}

@Composable
fun SettingsItem(
    text: String,
    asset: VectorAsset,
    modifier: Modifier = Constants.DefaultSettingsItemModifier,
    onClick: () -> Unit,
) {
    Row(
        modifier
            .clickable(onClick = onClick)
            .background(MaterialTheme.colors.surface, MaterialTheme.shapes.small)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            asset = asset.copy(
                defaultHeight = Constants.DefaultIconSize,
                defaultWidth = Constants.DefaultIconSize
            ),
            tint = MaterialTheme.colors.primaryVariant
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1
        )

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun SettingsBottomSheetPreview() {
    DefaultTheme {
        Surface(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            SettingsBottomSheet(sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded), content = emptyContent())
        }
    }
}


 object Constants {

    val DefaultSettingsItemModifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 16.dp)

    val DefaultIconSize = 30.dp

}

