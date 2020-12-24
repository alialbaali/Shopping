package com.shopping

import com.shopping.common.domain.LocalStorage

class FakeLocalStorage(private val storage: MutableMap<String, String> = mutableMapOf()) : LocalStorage {

    override suspend fun put(key: String, value: String): Result<Unit> {
        storage[key] = value
        return Result.success(Unit)
    }

    override suspend fun get(key: String): Result<String> {
        val value = storage[key] ?: return Result.failure(Throwable(""))
        return Result.success(value)
    }

}