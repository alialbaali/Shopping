package com.shopping.frontend.components

import com.shopping.common.Strings
import com.shopping.common.emailValidator
import com.shopping.common.passwordValidator
import com.shopping.common.textValidator
import com.shopping.frontend.Styles
import com.shopping.frontend.fc
import com.shopping.frontend.inputValue
import kotlinx.css.CSSBuilder
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import react.RProps
import styled.css
import styled.styledInput

external interface TextFieldProps : RProps {
    var value: String
    var onValueChange: (String) -> Unit
    var inputType: InputType?
    var placeHolder: String?
    var style: ((CSSBuilder) -> Unit)?
    var validator: ((String) -> Boolean)?
}

val TextField = fc<TextFieldProps> { props ->
    styledInput(type = props.inputType ?: InputType.text) {
        css {
            +Styles.Components.TextField
            props.style?.let { +it }
//            if (!props.validator(props.value)) borderColor = Colors.error
        }
        attrs {
            value = props.value
            placeholder = props.placeHolder ?: ""
            onChangeFunction = { props.onValueChange(it.inputValue) }
            autoFocus = true // Temporary
        }
    }
}

external interface DefaultInputProps : RProps {
    var value: String
    var onValueChange: (String) -> Unit
    var style: ((CSSBuilder) -> Unit)?
}

val NameTextField = fc<DefaultInputProps> { props ->
    TextField {
        inputType = InputType.text
        placeHolder = Strings.Name
        value = props.value
        onValueChange = props.onValueChange
        style = props.style
        validator = ::textValidator
    }
}

val EmailTextField = fc<DefaultInputProps> { props ->
    TextField {
        inputType = InputType.email
        placeHolder = Strings.Email
        value = props.value
        onValueChange = props.onValueChange
        style = props.style
        validator = ::emailValidator
    }
}

val PasswordTextField = fc<DefaultInputProps> { props ->
    TextField {
        inputType = InputType.password
        placeHolder = Strings.Password
        value = props.value
        onValueChange = props.onValueChange
        style = props.style
        validator = ::passwordValidator
    }
}

val SearchTextField = fc<DefaultInputProps> { props ->
    TextField {
        inputType = InputType.text
        placeHolder = Strings.Search
        value = props.value
        onValueChange = props.onValueChange
        style = props.style
        validator = ::textValidator
    }
}