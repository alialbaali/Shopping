package com.shopping.frontend.customer

import com.shopping.common.Strings
import com.shopping.frontend.components.TextField
import com.shopping.frontend.fc
import react.RProps
import react.getValue
import react.setValue
import react.useState


val NewAddressSection = fc<RProps> {

    var name by useState("")
    var country by useState("")
    var city by useState("")
    var line by useState("")
    var zipCode by useState("")

    TextField {
        value = name
        onValueChange = { name = it }
        placeHolder = Strings.Name
    }

    TextField {
        value = country
        onValueChange = { country = it }
        placeHolder = Strings.Country
    }

    TextField {
        value = city
        onValueChange = { city = it }
        placeHolder = Strings.City
    }

    TextField {
        value = line
        onValueChange = { line = it }
        placeHolder = Strings.Line
    }

    TextField {
        value = zipCode
        onValueChange = { zipCode = it }
        placeHolder = Strings.ZipCode
    }


}