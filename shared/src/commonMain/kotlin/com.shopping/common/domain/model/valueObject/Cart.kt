package com.shopping.common.domain.model.valueObject

import com.shopping.common.domain.model.Order

typealias CartItem = Order.OrderItem

inline class Cart(val value: Set<CartItem>) {

    companion object {
        val Empty = Cart(emptySet())
    }
}
