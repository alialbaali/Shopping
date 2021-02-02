package com.shopping.common.domain.client

internal object Endpoint {

    object Auth {

        private const val Root = "auth"

        const val SignUpRequest = "$Root/new"

        const val SignInRequest = "$Root/"

        const val RefreshToken = "$Root/"

    }

    object Customer {

        private const val Root = "customer"

        const val cards = "$Root/cards"

        const val GetCustomerById = Root

        const val UpdateCustomerRequest = Root

        const val UpdateCustomerImage = "$Root/image"

        const val UpdateCustomerPassword = "$Root/password"


    }

    object Product {

    }

    object Order {

    }

}