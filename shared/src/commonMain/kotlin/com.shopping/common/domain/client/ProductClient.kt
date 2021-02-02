package com.shopping.common.domain.client

import com.shopping.common.domain.dto.product.response.ProductResponse

internal interface ProductClient {

    suspend fun getProducts(limit: Long, offset: Long): Result<List<ProductResponse>>

}