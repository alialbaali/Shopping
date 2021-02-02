package com.shopping.frontend.product

import com.shopping.common.domain.model.Product
import com.shopping.frontend.fc
import com.shopping.frontend.Styles
import kotlinx.html.js.onClickFunction
import react.RProps
import styled.*


external interface ProductItemProps : RProps {
    var product: Product
    var onClick: (String) -> Unit
}


val ProductItem = fc<ProductItemProps> { props ->

    val product = props.product

    styledA("https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/i1-68df1a28-0740-48a2-97c0-092b84cbfc61/jordan-aerospace-720-mens-shoe-dBclR3.jpg") {

        css { +Styles.Products.Product.Root }
        attrs {
            onClickFunction = { props.onClick(product.id) }
        }

        styledImg(src = "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/i1-68df1a28-0740-48a2-97c0-092b84cbfc61/jordan-aerospace-720-mens-shoe-dBclR3.jpg") {
            css { +Styles.Products.Product.Image }
        }

        styledH1 {
            css { +Styles.Products.Product.Brand }
            +product.brand
        }

        styledH1 {
            css { +Styles.Products.Product.Name }
            +product.name
        }

        styledH1 {
            css { +Styles.Products.Product.Price }
            +"$${product.price}"
        }

    }
}