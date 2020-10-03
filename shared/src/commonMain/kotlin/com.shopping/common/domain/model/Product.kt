package com.shopping.common.domain.model

import com.shopping.common.domain.model.valueObject.Review
import com.shopping.common.now
import kotlinx.datetime.LocalDate

data class Product(
    val id: String,
    val category: Category,
    val brand: String,
    val name: String,
    val description: String,
    val price: Double,
    val imagesUrls: Set<String> = setOf(),
    val specs: Map<String, String> = mapOf(),
    val reviews: Set<Review> = setOf(),
    val releaseDate: LocalDate = LocalDate.now,
    val creationDate: LocalDate = LocalDate.now,
) {

    val avgRating: Double
        get() = reviews.sumBy { review ->
            review.rating.ordinal.plus(1)
        }.div(5.0)

    enum class Category {
        VideoGames, Movies, Books, Other
    }
}
