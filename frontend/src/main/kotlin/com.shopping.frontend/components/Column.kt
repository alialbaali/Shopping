package com.shopping.frontend.components

import com.shopping.frontend.Styles
import com.shopping.frontend.fc
import kotlinx.css.CSSBuilder
import react.RProps
import styled.css
import styled.styledDiv

external interface ColumnProps : RProps {
    var style: ((CSSBuilder) -> Unit)?
}

val Column = fc<ColumnProps> { props ->
    styledDiv {
        css {
            +Styles.Components.Column
            props.style?.let { +it }
        }
    }
}