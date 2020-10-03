package com.shopping.common.domain.dto.product.request

class CreateReviewRequest(
    val rating: Int,
    val description: String? = null,
)