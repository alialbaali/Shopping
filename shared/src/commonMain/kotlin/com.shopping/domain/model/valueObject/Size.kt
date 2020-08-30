package com.shopping.domain.model.valueObject


data class Size(
    val value: Long,
    val unitSize: UnitSize,
) {

    enum class UnitSize {
        CM, INCH, M, ELSE, FT, MM,
        X_SMALL, SMALL, MEDIUM, LARGE, X_LARGE
    }

}
