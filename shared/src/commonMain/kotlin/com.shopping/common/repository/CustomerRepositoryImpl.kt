package com.shopping.common.repository

import com.shopping.common.*
import com.shopping.common.IODispatcher
import com.shopping.common.domain.LocalStorage
import com.shopping.common.domain.client.AuthClient
import com.shopping.common.domain.client.CustomerClient
import com.shopping.common.domain.dto.customer.request.SignInRequest
import com.shopping.common.domain.dto.customer.request.SignUpRequest
import com.shopping.common.domain.dto.customer.request.UpdateCustomerPasswordRequest
import com.shopping.common.domain.dto.customer.request.UpdateCustomerRequest
import com.shopping.common.domain.dto.customer.response.CustomerResponse
import com.shopping.common.domain.model.Customer
import com.shopping.common.domain.model.valueObject.*
import com.shopping.common.domain.repository.CustomerRepository
import com.shopping.common.putTokenResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class CustomerRepositoryImpl(
    private val storage: LocalStorage,
    private val authClient: AuthClient,
    private val customerClient: CustomerClient,
    private val dispatcher: CoroutineDispatcher = IODispatcher,
) : CustomerRepository {

    override suspend fun signUp(name: String, email: Email, password: Password): Result<Unit> = withContext(dispatcher) {

        val email = email.toString()
        val password = password.toString()

        val signUpRequest = SignUpRequest(name, email, password)

        authClient.signUp(signUpRequest)
            .getOrElse { return@withContext Result.failure(it) }
            .also { storage.putTokenResponse(it, dispatcher) }
            .let { Result.success(Unit) }
    }

    override suspend fun signIn(email: Email, password: Password): Result<Unit> = withContext(dispatcher) {

        val email = email.toString()
        val password = password.toString()

        val signInRequest = SignInRequest(email, password)

        authClient.signIn(signInRequest)
            .getOrElse { return@withContext Result.failure(it) }
            .also { storage.putTokenResponse(it, dispatcher) }
            .let { Result.success(Unit) }
    }

    override suspend fun getCustomer(): Result<Customer> = withContext(dispatcher) {
        customerClient.getCustomer()
            .getOrElse { return@withContext Result.failure(it) }
            .toCustomer()
            .let { Result.success(it) }
    }

    override suspend fun updateCustomer(customer: Customer): Result<Customer> = withContext(dispatcher) {
        val name = customer.name
        val email = customer.email.toString()

        val updateCustomerRequest = UpdateCustomerRequest(name, email)

        customerClient.updateCustomer(updateCustomerRequest)
            .getOrElse { return@withContext Result.failure(it) }
            .toCustomer()
            .let { Result.success(it) }
    }

    override suspend fun updateCustomerPassword(oldPassword: Password, newPassword: Password): Result<Unit> = withContext(dispatcher) {
        val updateCustomerPasswordRequest = UpdateCustomerPasswordRequest(oldPassword.toString(), newPassword.toString())

        customerClient.updateCustomerPassword(updateCustomerPasswordRequest)
            .getOrElse { return@withContext Result.failure(it) }
            .let { Result.success(it) }
    }

    override suspend fun updateCustomerImage(file: File): Result<String> = withContext(dispatcher) {
        customerClient.updateCustomerImage(file.toByteArray())
    }

    override suspend fun deleteCustomer(): Result<Unit> = withContext(dispatcher) {
        customerClient.deleteCustomer()
            .onSuccess { storage.clear() }
            .apply { signOut() }
    }

    override suspend fun getAddresses(): Result<List<Address>> {
        TODO("Not yet implemented")
    }

    override suspend fun createAddress(address: Address): Result<Address> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAddressByName(name: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getCards(): Result<List<Card>> {
        TODO("Not yet implemented")
    }

    override suspend fun createCard(card: Card): Result<Card> {
        TODO("Not yet implemented")
    }

}

private fun CustomerResponse.toCustomer(): Customer {
    return Customer(
        id,
        name,
        Email(email),
        Password(String()),
        imageUrl,
        Cart.Empty
    )
}
