package com.shopping.frontend.customer

import com.shopping.common.Strings
import com.shopping.frontend.components.PasswordTextField
import com.shopping.frontend.components.PrimaryButton
import com.shopping.frontend.fc
import react.RProps
import react.getValue
import react.setValue
import react.useState

val ChangePasswordSection = fc<RProps> {

    var currentPassword by useState("")
    var newPassword by useState("")

    PasswordTextField {
        value = currentPassword
        onValueChange = { currentPassword = it }
    }

    PasswordTextField {
        value = newPassword
        onValueChange = { newPassword = it }
    }

    PrimaryButton {
        text = Strings.Update
        onClick = {

        }
    }

}