package com.shopping.common.client

import com.shopping.common.authHeader
import com.shopping.common.domain.client.OrderClient
import com.shopping.common.domain.dto.order.response.OrderDetailsResponse
import com.shopping.common.domain.dto.order.response.OrderResponse
import com.shopping.common.withCustomer
import io.ktor.client.*
import io.ktor.client.request.*

private const val BaseUrl = "orders"

internal class OrderClientImpl(private val client: HttpClient): OrderClient {
    
    override suspend fun getOrdersByCustomerId(customerId: String): Result<List<OrderResponse>> = client.withCustomer { accessToken, _ ->
        get("$BaseUrl/") {
            authHeader(accessToken)
        }
    }

    override suspend fun getOrder(customerId: String, orderId: String): Result<OrderDetailsResponse> = client.withCustomer { accessToken, _ ->
        get("$BaseUrl/") {
            authHeader(accessToken)
        }
    }

    override suspend fun createOrder(customerId: String, orderId: String): Result<OrderDetailsResponse> {
        TODO("Not yet implemented")
    }


}