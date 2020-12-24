package com.shopping.android.auth

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.shopping.android.Screen
import com.shopping.android.components.*
import com.shopping.common.ServiceLocator
import com.shopping.common.Strings
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController) {

    val scope = rememberCoroutineScope()

//    val viewModel = ServiceLocator.ViewModel.customerViewModel

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        NameTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        EmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )

        Spacer(modifier = Modifier.height(32.dp))

        PrimaryButton(
            text = Strings.SignUp,
            onClick = {
                scope.launch {
//                    viewModel.signUp(name, email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Text(text = Strings.AlreadyMember)

            SecondaryButton(
                text = Strings.SignIn,
                onClick = { navController.navigate(Screen.SignIn.route) },
            )
        }

    }

}

//@Preview
//@Composable
//private fun SignUpScreenPreview() {
//    DefaultTheme {
//        SignUpScreen(rememberNavController())
//    }
//}