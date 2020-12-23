package com.shopping.common.domain.repository

import com.shopping.common.domain.model.Order

internal interface OrderRepository {

    suspend fun getOrders(): Result<List<Order>>

    suspend fun getOrder(orderId: String): Result<Order>

    suspend fun createOrder(order: Order): Result<Order>

}