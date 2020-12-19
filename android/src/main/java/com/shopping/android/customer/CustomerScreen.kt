package com.shopping.android.customer

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview
import com.shopping.android.DefaultIcons
import com.shopping.android.Screen
import com.shopping.android.ui.DefaultTheme
import com.shopping.android.ui.sheet
import com.shopping.android.verticalGradient
import com.shopping.common.DummyData
import com.shopping.common.Strings
import dev.chrisbanes.accompanist.coil.CoilImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomerScreen(navController: NavController) {

    val customer = DummyData.customer

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalGradient(listOf(MaterialTheme.colors.primary, Color.White), 0F, 500F),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val size = 125.dp
        val offset = size.div(2.dp)
        val scrollableState = rememberScrollState()
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)

        Box(
//            data = customer.imageUrl,
            modifier = Modifier
                .offset(y = offset.dp)
                .drawShadow(4.dp, MaterialTheme.shapes.large)
                .zIndex(1F)
                .size(size)
                .background(MaterialTheme.colors.primary, MaterialTheme.shapes.large)
        )

        Column(
            Modifier
                .drawShadow(8.dp, MaterialTheme.shapes.sheet)
                .fillMaxSize()
                .background(Color.White, MaterialTheme.shapes.sheet)
                .verticalScroll(scrollableState)
        ) {

            Spacer(
                Modifier
                    .height(offset.dp.plus(12.dp))
            )

            Text(
                text = customer.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )

            Text(
                text = customer.email.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground.copy(0.5F)
            )

            Spacer(Modifier.height(32.dp))

            SettingsItem(
                text = Strings.Orders,
                asset = DefaultIcons.Receipt,
            ) { navController.navigate(Screen.Orders.route) }

            Spacer(Modifier.height(16.dp))

            SettingsItem(
                text = Strings.Addresses,
                asset = DefaultIcons.LocationCity,
            ) { navController.navigate(Screen.Addresses.route) }

            Spacer(Modifier.height(16.dp))

            SettingsItem(
                text = Strings.Cards,
                asset = DefaultIcons.CreditCard,
            ) {
                navController.navigate(Screen.Cards.route)
            }

            Spacer(Modifier.height(16.dp))

            SettingsItem(
                text = Strings.Settings,
                asset = DefaultIcons.Settings,
            ) { sheetState.animateTo(ModalBottomSheetValue.Expanded) }


            SettingsBottomSheet(rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded))

        }
    }

}

@Preview(device = Devices.PIXEL_4_XL)
@Composable
private fun CustomerScreenPreview() {
    DefaultTheme {
        CustomerScreen(rememberNavController())
    }
}