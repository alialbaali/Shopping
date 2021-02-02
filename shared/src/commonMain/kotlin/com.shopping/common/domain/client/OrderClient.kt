package com.shopping.common.domain.client

import com.shopping.common.domain.dto.order.response.OrderDetailsResponse
import com.shopping.common.domain.dto.order.response.OrderResponse

internal interface OrderClient {

    suspend fun getOrdersByCustomerId(customerId: String): Result<List<OrderResponse>>

    suspend fun getOrder(customerId: String, orderId: String): Result<OrderDetailsResponse>

    suspend fun createOrder(customerId: String, orderId: String): Result<OrderDetailsResponse>

}