package com.shopping.common.domain.dto.customer.request


class CreateAddressRequest(
    val name: String,
    val country: String,
    val city: String,
    val line: String,
    val zipCode: String,
)