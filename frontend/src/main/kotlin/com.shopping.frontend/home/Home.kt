package com.shopping.frontend.home

import com.shopping.common.Strings
import com.shopping.common.domain.model.Product
import com.shopping.frontend.Styles
import com.shopping.frontend.components.SearchTextField
import com.shopping.frontend.fc
import com.shopping.frontend.product.ProductItem
import kotlinx.css.Image
import kotlinx.css.backgroundImage
import kotlinx.html.js.onClickFunction
import react.*
import styled.*

val Home = fc<RProps> {
    styledDiv {
        key = "DIV 2"
        css { +Styles.Root }
        NavBar {
            key = "NAVBAR"
        }
//        Products {
//            products = DummyData.products
//        }
    }
}

val NavBar = fc<RProps> {

    var search by useState("")

    styledDiv {

        css {
            backgroundImage =
                Image("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/220px-Image_created_with_a_mobile_phone.png")
            +Styles.NavBar.Root
        }

        styledH1 {
            +Strings.WebsiteName
            css { +Styles.NavBar.Title }
        }

        SearchTextField {
            value = search
            onValueChange = { search = it }
        }

        styledA(href = "https://alialbaali.netlify.app/static/me.jpg") {
            +Strings.Home
            css { +Styles.NavBar.Item }
        }

        styledA(href = "https://alialbaali.netlify.app/static/me.jpg") {
            +Strings.Cart
            css { +Styles.NavBar.Item }
        }

        styledA("https://alialbaali.netlify.app/static/me.jpg") {
            styledImg(src = "https://alialbaali.netlify.app/static/me.jpg", alt = "User name") {
                css { +Styles.NavBar.Profile }
            }
        }
    }

}

external interface ProductsProps : RProps {
    var products: List<Product>
}

val Products = fc<ProductsProps> { props ->
    styledDiv {
        css { +Styles.Products.Root }
        props.products.forEach { product ->
            ProductItem {
                this.product = product
                this.onClick = {

                }
            }
        }
    }
}

private external interface CategoriesProps : RProps {
    var onClick: (Product.Category) -> Unit
}

private val Categories = fc<CategoriesProps> { props ->
    styledDiv {
        css { +Styles.Products.Categories }
        Product.Category.values().forEach { category ->
            Category {
                this.category = category
                onClick = { props.onClick(it) }
            }
        }
    }
}

private external interface CategoryProps : RProps {
    var category: Product.Category
    var onClick: (Product.Category) -> Unit
}

private val Category = fc<CategoryProps> { props ->
    val category = props.category
    styledA("https://www.google.com") {
        +category.name
        css { +Styles.Products.Category }
        attrs {
            onClickFunction = {
                props.onClick(category)
            }
        }
    }
}

