package com.shopping.common.domain.client

import com.shopping.common.domain.dto.customer.request.*
import com.shopping.common.domain.dto.customer.response.AddressResponse
import com.shopping.common.domain.dto.customer.response.CardResponse
import com.shopping.common.domain.dto.customer.response.CartItemResponse
import com.shopping.common.domain.dto.customer.response.CustomerResponse


internal interface CustomerClient {

    suspend fun getCustomer(): Result<CustomerResponse>

    suspend fun updateCustomer(updateCustomerRequest: UpdateCustomerRequest): Result<CustomerResponse>

    suspend fun updateCustomerPassword(updateCustomerPasswordRequest: UpdateCustomerPasswordRequest): Result<Unit>

    suspend fun updateCustomerImage(byteArray: ByteArray): Result<String>

    suspend fun deleteCustomer(): Result<Unit>

    suspend fun getCart(): Result<List<CartItemResponse>>

    suspend fun createCartItem(createCartItemRequest: CreateCartItemRequest): Result<CartItemResponse>

    suspend fun updateCartItem(updateCartItemRequest: UpdateCartItemRequest): Result<CartItemResponse>

    suspend fun deleteCartItem(productId: String): Result<Unit>

    suspend fun getAddresses(): Result<List<AddressResponse>>

    suspend fun createAddress(createAddressRequest: CreateAddressRequest): Result<AddressResponse>

    suspend fun deleteAddress(addressName: String): Result<Unit>

    suspend fun getCards(): Result<List<CardResponse>>

    suspend fun createCard(createCardRequest: CreateCardRequest): Result<CardResponse>

    suspend fun deleteCard(cardLast4Numbers: Long): Result<Unit>

    companion object {

        fun CustomersEndpoint() = ""
    }

}