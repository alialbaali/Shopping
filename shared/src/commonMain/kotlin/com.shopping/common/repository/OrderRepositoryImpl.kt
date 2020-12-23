package com.shopping.common.repository

import com.shopping.common.domain.client.OrderClient
import com.shopping.common.domain.model.Order
import com.shopping.common.domain.repository.OrderRepository

internal class OrderRepositoryImpl(private val orderClient: OrderClient) : OrderRepository {
    override suspend fun getOrders(): Result<List<Order>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrder(orderId: String): Result<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun createOrder(order: Order): Result<Order> {
        TODO("Not yet implemented")
    }
}