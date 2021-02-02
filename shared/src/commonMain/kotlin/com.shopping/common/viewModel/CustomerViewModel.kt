package com.shopping.common.viewModel

import com.shopping.common.Resource
import com.shopping.common.domain.model.Customer
import com.shopping.common.domain.model.valueObject.Email
import com.shopping.common.domain.model.valueObject.Password
import com.shopping.common.domain.repository.CustomerRepository
import com.shopping.common.isSignedIn
import com.shopping.common.toResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow


class CustomerViewModel internal constructor(private val customerRepository: CustomerRepository) {

//    private val _customer = MutableStateFlow<Resource<Customer>>(Resource.Loading())
//    val customer = _customer.asStateFlow()
//
//    private val _error = MutableStateFlow<String?>(null)
//    val error = _error.asStateFlow()
//
//    private val _shouldNavigate = MutableStateFlow(false)
//    val shouldNavigate = _shouldNavigate.asStateFlow()
//
//    suspend fun getCustomer() {
//        if (isSignedIn) {
//            _customer.value = customerRepository
//                .getCustomer()
//                .toResource()
//        }
//    }
//
//    suspend fun signUp(name: String, email: String, password: String) {
//        customerRepository.signUp(name, Email(email), Password(password))
//            .fold(
//                onSuccess = { _shouldNavigate.value = true },
//                onFailure = { _error.value = it.message }
//            )
//    }
//
//    suspend fun signIn(email: String, password: String) {
//        customerRepository.signIn(Email(email), Password(password))
//            .fold(
//                onSuccess = { _shouldNavigate.value = true },
//                onFailure = { _error.value = it.message }
//            )
//    }
//
//    suspend fun updatePassword(oldPassword: String, newPassword: String) {
//        customerRepository.updateCustomerPassword(Password(oldPassword), Password(newPassword))
//            .fold(
//                onSuccess = { _shouldNavigate.value = true },
//                onFailure = { _error.value = it.message }
//            )
//    }

}

