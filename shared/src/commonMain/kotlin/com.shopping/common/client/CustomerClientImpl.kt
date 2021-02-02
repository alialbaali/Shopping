package com.shopping.common.client

import com.shopping.common.DefaultResponse
import com.shopping.common.authHeader
import com.shopping.common.domain.client.CustomerClient
import com.shopping.common.domain.dto.customer.request.*
import com.shopping.common.domain.dto.customer.response.AddressResponse
import com.shopping.common.domain.dto.customer.response.CardResponse
import com.shopping.common.domain.dto.customer.response.CartItemResponse
import com.shopping.common.domain.dto.customer.response.CustomerResponse
import com.shopping.common.withCustomer
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.utils.io.core.*

private const val BaseUrl = "customers"

internal class CustomerClientImpl(private val client: HttpClient) : CustomerClient {

    override suspend fun getCustomer(): Result<CustomerResponse> =
        client.withCustomer { accessToken, customerId ->
            get("$BaseUrl/${customerId}") {
                authHeader(accessToken)
            }
        }

    override suspend fun updateCustomer(updateCustomerRequest: UpdateCustomerRequest): Result<CustomerResponse> =
        client.withCustomer { accessToken, customerId ->
            patch("$BaseUrl/${customerId}") {
                authHeader(accessToken)
                body = updateCustomerRequest
            }
        }

    override suspend fun updateCustomerPassword(updateCustomerPasswordRequest: UpdateCustomerPasswordRequest): Result<Unit> =
        client.withCustomer { accessToken, customerId ->
            patch("$BaseUrl/${customerId}/password") {
                authHeader(accessToken)
                body = updateCustomerPasswordRequest
            }
        }

    override suspend fun updateCustomerImage(byteArray: ByteArray): Result<String> =
        client.withCustomer<DefaultResponse> { accessToken, customerId ->
            post("$BaseUrl/$customerId/image") {
                authHeader(accessToken)
                body = MultiPartFormDataContent(
                    formData {
                        append(customerId, customerId, ContentType.Image.Any, byteArray.size.toLong()) {
                            writeFully(byteArray)
                        }
                    }
                )
            }
        }.mapCatching { it["image_url"] ?: return Result.failure(Throwable()) }

    override suspend fun deleteCustomer(): Result<Unit> =
        client.withCustomer { accessToken, customerId ->
            delete("$BaseUrl/${customerId}")
        }

    override suspend fun getCart(): Result<List<CartItemResponse>> = client.withCustomer { accessToken, customerId ->
        get("$BaseUrl/${customerId}/cards") {
            authHeader(accessToken)
        }
    }

    override suspend fun createCartItem(createCartItemRequest: CreateCartItemRequest): Result<CartItemResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCartItem(updateCartItemRequest: UpdateCartItemRequest): Result<CartItemResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCartItem(productId: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAddresses(): Result<List<AddressResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun createAddress(createAddressRequest: CreateAddressRequest): Result<AddressResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAddress(addressName: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getCards(): Result<List<CardResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun createCard(createCardRequest: CreateCardRequest): Result<CardResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCard(cardLast4Numbers: Long): Result<Unit> {
        TODO("Not yet implemented")
    }

}

