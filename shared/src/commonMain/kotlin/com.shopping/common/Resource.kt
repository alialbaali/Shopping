package com.shopping.common


sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val exception: Throwable) : Resource<T>()

    val isSuccess: Boolean
        get() = this is Success<T>

    val isFailure: Boolean
        get() = this is Failure<T>

    val isLoading: Boolean
        get() = this is Loading<T>

}

fun <T> Result<T>.toResource(): Resource<T> {
    return fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Failure(it) },
    )
}