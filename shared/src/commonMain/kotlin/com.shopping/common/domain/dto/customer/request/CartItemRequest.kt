package com.shopping.common.domain.dto.customer.request


class CreateCartItemRequest(
    val productId: String,
    val quantity: Long,
)

typealias UpdateCartItemRequest = CreateCartItemRequest
