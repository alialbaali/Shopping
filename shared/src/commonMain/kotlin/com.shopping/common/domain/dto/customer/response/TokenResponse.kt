package com.shopping.common.domain.dto.customer.response

data class TokenResponse(
    val customerId: String,
    val accessToken: String,
    val refreshToken: String,
)
