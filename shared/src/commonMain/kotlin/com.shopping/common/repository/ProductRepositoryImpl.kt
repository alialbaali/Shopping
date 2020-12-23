package com.shopping.common.repository

import com.shopping.common.domain.client.ProductClient
import com.shopping.common.domain.dto.product.response.ProductResponse
import com.shopping.common.domain.model.Product
import com.shopping.common.domain.repository.ProductRepository

internal class ProductRepositoryImpl(private val productClient: ProductClient) : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>> {

        val products = productClient.getProducts(0, 0).getOrElse {
            return Result.failure(it)
        }.map { productResponse ->
            productResponse.toProduct()
        }

        return Result.success(products)
    }

}

private fun ProductResponse.toProduct(): Product {
    return Product(id, Product.Category.valueOf(category), brand, name, "description", price, setOf(image), emptyMap(), emptySet())
}