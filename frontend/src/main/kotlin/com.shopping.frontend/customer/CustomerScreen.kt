package com.shopping.frontend.customer

import com.shopping.common.DummyData
import com.shopping.common.Strings
import com.shopping.frontend.Styles
import com.shopping.frontend.fc
import react.RProps
import styled.*

val CustomerScreen = fc<RProps> {

    val customer = DummyData.customer

    styledDiv {
        css { +Styles.Customer.Root }

        Aside {

        }

        styledDiv {

            css { +Styles.Customer.Main }

//        styledImg(src = customer.imageUrl) {
//            css { +Styles.Customer.Image }
//        }

            styledH1 {
                css { +Styles.Customer.Name }
                +customer.name
            }

            styledH3 {
                css { +Styles.Customer.Email }
                +customer.email.toString()
            }
        }
    }
}

private val Aside = fc<RProps> {

    styledAside {
        css { +Styles.Customer.Aside.Root }

        AsideItem {
            text = Strings.EditProfile
        }
        AsideItem {
            text = Strings.ChangePassword
        }

        AsideItem {
            text = Strings.SignOut
        }

        AsideItem {
            text = Strings.DeleteAccount
        }

    }
}

external interface AsideItemProps : RProps {
    var text: String
}

private val AsideItem = fc<AsideItemProps> { props ->
    styledA {
        css { +Styles.Customer.Aside.AsideItem }
        +props.text
    }
}