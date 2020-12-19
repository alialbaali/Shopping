package com.shopping.common.domain.model.valueObject

import com.shopping.common.now
import kotlinx.datetime.LocalDate

data class Review(
    val customerId: String,
    val rating: Rating,
    val description: String? = null,
    val creationDate: LocalDate = LocalDate.now,
)

enum class Rating {
    One, Two, Three, Four, Five
}
