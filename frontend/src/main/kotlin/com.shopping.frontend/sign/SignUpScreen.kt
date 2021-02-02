package com.shopping.frontend.sign

import com.shopping.common.Strings
import com.shopping.frontend.Styles
import com.shopping.frontend.components.EmailTextField
import com.shopping.frontend.components.NameTextField
import com.shopping.frontend.components.PasswordTextField
import com.shopping.frontend.components.PrimaryButton
import com.shopping.frontend.emptyProps
import com.shopping.frontend.fc
import kotlinx.browser.window
import react.RProps
import react.getValue
import react.setValue
import react.useState
import styled.css
import styled.styledDiv
import styled.styledForm

val SignUpScreen = fc<RProps> {
    styledDiv {
        css { +Styles.SignScreen.Root }

        SignUpForm(emptyProps())
    }
}

private val SignUpForm = fc<RProps> {

    var name by useState("")
    var email by useState("")
    var password by useState("")

    styledForm {

        css { +Styles.SignScreen.SignForm }

        NameTextField {
            value = name
            onValueChange = { name = it }
            style = { with(it) { +Styles.SignScreen.Input } }
        }

        EmailTextField {
            value = email
            onValueChange = { email = it }
            style = { with(it) { +Styles.SignScreen.Input } }
        }

        PasswordTextField {
            value = password
            onValueChange = { password = it }
            style = { with(it) { +Styles.SignScreen.Input } }
        }

        PrimaryButton {
            text = Strings.SignUp
            onClick = { window.alert("Clicked") }
            style = { with(it) { +Styles.SignScreen.Button } }
        }

    }

}




