package com.shopping.frontend

import com.shopping.common.LocalStorageImpl
import com.shopping.common.ServiceLocator
import com.shopping.frontend.components.Column
import com.shopping.frontend.components.Row
import com.shopping.frontend.customer.CustomerScreen
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import react.dom.render
import styled.styledDiv

fun main() {

    ServiceLocator(LocalStorageImpl(localStorage))

    window.onload = {
        render(document.getElementById("root")) {
            Row {
                repeat(10) {
                    +styledDiv {
                        +it.toString()
                    }
                }
            }
//            Home(emptyProps())
//            SignInScreen(emptyProps())

        }
    }
}
