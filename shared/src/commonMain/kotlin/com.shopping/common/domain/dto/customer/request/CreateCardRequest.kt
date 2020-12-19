package com.shopping.common.domain.dto.customer.request

class CreateCardRequest(
    val number: String,
    val expMonth: Int,
    val expYear: Int,
    val cvc: Int,
)