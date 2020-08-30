package com.shopping.domain.model.valueObject

import kotlinx.datetime.LocalDate


data class Card(
    val brand: String = String(),
    val number: Long,
    val expirationDate: LocalDate,
    val cvc: Long = 0,
)