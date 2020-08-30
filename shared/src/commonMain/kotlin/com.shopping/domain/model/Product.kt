package com.shopping.domain.model

import com.shopping.domain.model.valueObject.ID
import com.shopping.domain.model.valueObject.Review
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

data class Product(
    val id: ID = ID.random(),
    val category: Category,
    val brand: String,
    val name: String,
    val description: String,
    val price: Double,
    val imagesUrls: Set<String> = setOf(),
    val specs: Map<String, String> = mapOf(),
    val reviews: Set<Review> = setOf(),
    val releaseDate: LocalDate,
    val creationDate: LocalDate = Clock.System.todayAt(TimeZone.currentSystemDefault()),
) {

    enum class Category {
        VideoGames, Movies, Books, Other
    }

}