package com.shopping.common.client

import com.shopping.common.domain.client.ProductClient
import com.shopping.common.domain.dto.product.response.ProductResponse
import io.ktor.client.*
import io.ktor.client.request.*

private const val Products = "products"
private const val Limit = "limit"
private const val Offset = "offset"

internal class ProductClientImpl(private val client: HttpClient) : ProductClient {

    override suspend fun getProducts(limit: Long, offset: Long): Result<List<ProductResponse>> = client.get(Products) {
        parameter(Limit, limit)
        parameter(Offset, offset)
    }

}