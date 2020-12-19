package com.shopping.frontend.components

import com.shopping.frontend.Styles
import com.shopping.frontend.fc
import kotlinx.css.CSSBuilder
import kotlinx.html.js.onClickFunction
import react.RProps
import styled.css
import styled.styledButton

external interface PrimaryButtonProps : RProps {
    var text: String
    var onClick: () -> Unit
    var style: ((CSSBuilder) -> Unit)?
}

val PrimaryButton = fc<PrimaryButtonProps> { props ->
    styledButton {
        +props.text
        css {
            +Styles.Components.PrimaryButton
            props.style?.let { +it }
        }
        attrs { onClickFunction = { props.onClick() } }
    }

}