package com.shopping.common

import com.shopping.common.domain.model.Customer
import com.shopping.common.domain.model.Product
import com.shopping.common.domain.model.valueObject.*
import kotlinx.datetime.LocalDate
import kotlin.random.Random

object DummyData {

    val customer = Customer(
        "",
        "John Doe",
        Email("johndoe@mail.com"),
        Password("Password"),
    ).let {

        val cartItems = mutableListOf<CartItem>()
        val addresses = mutableListOf<Address>()
        val cards = mutableListOf<Card>()

        repeat(20) { value ->

            val address = Address(
                if (value % 2 == 0) "Home" else "Work",
                "USA",
                "New York",
                "Emek Mahallesi, Ordu CD. Aqua CIty 2010 T Blok Daire 23",
                "34752",
            )

            val card = Card(
                if (value % 2 == 0) "Visa" else "MasterCard",
                4242_0000_0000_4242,
                LocalDate.now,
            )

            val cartItem = CartItem(
                "Random.toString()",
                value.toLong()
            )

            cartItems.add(cartItem)
            cards.add(card)
            addresses.add(address)
        }

        it.copy(
            addresses = addresses.toSet(),
            cards = cards.toSet(),
            cart = Cart(cartItems.toSet())
        )
    }

    val products = mutableListOf<Product>().apply {
        repeat(100) {
            val reviews = mutableSetOf<Review>()
            val specs = mutableMapOf<String, String>()

            repeat(25) {
                val review = Review(
                    it.toString(), Rating.values().random(),
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla erat lacus, consectetur at eleifend pulvinar, malesuada quis quam. Duis blandit scelerisque metus vel elementum. Nullam vestibulum elit at lacus ullamcorper ornare. Morbi semper, dolor a rhoncus hendrerit, orci augue gravida leo, at viverra erat magna ac lectus. Praesent porta blandit lorem, euismod condimentum enim lobortis sed. Quisque cursus placerat ipsum a lacinia. Vivamus sed urna in enim maximus dapibus. Etiam ac cursus velit. Proin in pellentesque tellus."
                )
                reviews.add(review)
                specs["Released"] = "2016"
            }
            add(
                Product(
                    it.toString(),
                    Product.Category.values().random(),
                    "Apple",
                    "IPad Pro 7th Gen Retina Display with TouchScreen",
                    "",
                    1399.0,
                    emptySet(),
                    specs,
                    reviews,
                )
            )
        }
    }

}