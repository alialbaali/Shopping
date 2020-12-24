package com.shopping.android

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.shopping.android.auth.SignInScreen
import com.shopping.android.auth.SignUpScreen
import com.shopping.android.customer.CustomerScreen
import com.shopping.android.customer.SettingsBottomSheet
import com.shopping.android.list.ProductsScreen
import com.shopping.android.order.OrderDetailsScreen
import com.shopping.android.order.OrdersScreen
import com.shopping.android.ui.DefaultTheme

sealed class Screen(private val baseRoute: String, param: String = EmptyParam) {

    val route = if (param.isEmpty()) baseRoute else "$baseRoute/{$param}"

    companion object {

        private const val EmptyParam = ""

        fun Screen.withParam(param: String): String = "$baseRoute/$param"

    }

    object Products : Screen("products")
    object Product : Screen("product", "product-id")

    object Customer : Screen("customer")

    object SignUp : Screen("sign-up")
    object SignIn : Screen("sign-in")

    object Cart : Screen("cart")

    object Orders : Screen("orders")
    object Order : Screen("orders", "order-id")

    object Addresses : Screen("addresses")
    object Cards : Screen("cards")

    object Settings : Screen("settings")

    object EditInfo : Screen("edit-info")

    object EditPassword : Screen("edit-password")


}

@Composable
fun App() {
    val navController = rememberNavController()
    DefaultTheme {
        NavHost(navController = navController, startDestination = Screen.Customer.route) {

            composable(Screen.SignUp.route) { SignUpScreen(navController) }
            composable(Screen.SignIn.route) { SignInScreen(navController) }

            composable(Screen.Customer.route) { CustomerScreen(navController) }

            composable(Screen.Orders.route) { OrdersScreen(navController) }
            composable(Screen.Order.route, listOf(stringNavArgument("order-id"))) { OrderDetailsScreen(navController) }

            composable(Screen.Products.route) { ProductsScreen(navController) }
            composable(Screen.Product.route, listOf(stringNavArgument("product-id"))) { CustomerScreen(navController) }
        }
    }
}

private fun stringNavArgument(name: String, nullable: Boolean = false, defaultValue: String?= null) = navArgument(name) {
    type = NavType.StringType
    this.nullable = nullable
    this.defaultValue = defaultValue
}
