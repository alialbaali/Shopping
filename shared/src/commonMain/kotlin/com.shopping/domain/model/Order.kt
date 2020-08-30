package com.shopping.domain.model

import com.shopping.domain.model.valueObject.Address
import com.shopping.domain.model.valueObject.Card
import com.shopping.domain.model.valueObject.ID
import kotlinx.datetime.*

data class Order(
    val id: ID = ID.random(),
    val customerID: ID,
    val productsIds: List<ID>,
    val address: Address,
    val card: Card,
    val creationDate: LocalDate = Clock.System.todayAt(TimeZone.currentSystemDefault()),
) {

    data class OrderItem(
        val productId: ID,
        val quantity: Long,
        val price: Double,
    )

}