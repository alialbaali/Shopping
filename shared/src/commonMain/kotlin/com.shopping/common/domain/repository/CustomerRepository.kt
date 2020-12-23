package com.shopping.common.domain.repository

import com.shopping.common.File
import com.shopping.common.domain.model.Customer
import com.shopping.common.domain.model.valueObject.Address
import com.shopping.common.domain.model.valueObject.Card
import com.shopping.common.domain.model.valueObject.Email
import com.shopping.common.domain.model.valueObject.Password

internal interface CustomerRepository {

    suspend fun signUp(name: String, email: Email, password: Password): Result<Unit>

    suspend fun signIn(email: Email, password: Password): Result<Unit>

    suspend fun getCustomer(): Result<Customer>

    suspend fun updateCustomer(customer: Customer): Result<Customer>

    suspend fun updateCustomerPassword(oldPassword: Password, newPassword: Password): Result<Unit>

    suspend fun updateCustomerImage(file: File): Result<String>

    suspend fun deleteCustomer(): Result<Unit>

    suspend fun getAddresses(): Result<List<Address>>

    suspend fun createAddress(address: Address): Result<Address>

    suspend fun deleteAddressByName(name: String): Result<Unit>

    suspend fun getCards(): Result<List<Card>>

    suspend fun createCard(card: Card): Result<Card>

//    suspend fun deleteCardByLast4Numbers(number: Long): Result<Unit>

}