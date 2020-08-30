package com.shopping.domain.model

import com.shopping.domain.model.valueObject.*
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

private const val CUSTOMER_DEFAULT_IMAGE_URL = "https://res.cloudinary.com/shopping-cloud/image/upload/v1598449571/placeHolder_elsjfb.png"

data class Customer(
    val id: ID = ID.random(),
    val name: String,
    val email: Email,
    val password: Password,
    val imageUrl: String = CUSTOMER_DEFAULT_IMAGE_URL,
    val addresses: Set<Address> = setOf(),
    val cards: Set<Card> = setOf(),
    val creationDate: LocalDate = Clock.System.todayAt(TimeZone.currentSystemDefault()),
)