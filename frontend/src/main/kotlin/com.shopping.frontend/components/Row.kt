package com.shopping.frontend.components

import com.shopping.frontend.Styles
import com.shopping.frontend.fc
import kotlinx.css.CSSBuilder
import react.RProps
import styled.css
import styled.styledDiv

external interface RowProps : RProps {
    var style: ((CSSBuilder) -> Unit)?
}

val Row = fc<RowProps> { props ->
    styledDiv {
        css {
            +Styles.Components.Row
            props.style?.let { +it }
        }
    }
}