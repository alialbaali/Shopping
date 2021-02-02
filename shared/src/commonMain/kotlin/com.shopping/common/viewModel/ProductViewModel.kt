package com.shopping.common.viewModel

import com.shopping.common.Resource
import com.shopping.common.domain.model.Product
import com.shopping.common.domain.repository.ProductRepository
import com.shopping.common.toResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModel internal constructor(private val productRepository: ProductRepository) {

//    private val _products = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
//    val products = _products.asStateFlow()

//    private val _error = MutableStateFlow<String?>(null)
//    val error = _error.asStateFlow()

    suspend fun getProducts() {
//        _products.value = productRepository.getProducts()
//            .onFailure { _error.value = it.message }
//            .toResource()
    }

}