package com.shopping.common.domain.repository

import com.shopping.common.domain.model.Product

internal interface ProductRepository {

    suspend fun getProducts(): Result<List<Product>>

}