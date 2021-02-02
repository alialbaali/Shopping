package com.shopping.frontend.customer

import com.shopping.common.DummyData
import com.shopping.common.Strings
import com.shopping.frontend.components.EmailTextField
import com.shopping.frontend.components.NameTextField
import com.shopping.frontend.components.PrimaryButton
import com.shopping.frontend.fc
import react.RProps
import react.getValue
import react.setValue
import react.useState

val EditProfileSection = fc<RProps> {

    val customer = DummyData.customer

    var name by useState(customer.name)
    var email by useState(customer.email.toString())

    NameTextField {
        value = name
        onValueChange = { name = it }
    }

    EmailTextField {
        value = email
        onValueChange = { email = it }
    }

    PrimaryButton {
        text = Strings.Update
        onClick = {

        }
    }

}