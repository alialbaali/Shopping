package com.shopping.common.domain.model.valueObject

inline class Password(private val value: String = "") {

    companion object {

        private val Regex = Regex("^(?=.*?[0-9]).{8,}$")

        fun create(password: String): Result<Password> =
            if (password.matches(Regex)) {
                Result.success(Password(password))
            } else {
                Result.failure(Throwable())
            }
    }

    override fun toString(): String = value
}
