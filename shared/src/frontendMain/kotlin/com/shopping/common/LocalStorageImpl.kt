package com.shopping.common

import com.shopping.common.domain.LocalStorage
import org.w3c.dom.Storage

actual class LocalStorageImpl(private val storage: Storage) : LocalStorage {

    override suspend fun put(key: String, value: String): Result<Unit> = runCatching {
        storage.setItem(key, value)
        return Result.success(Unit)
    }

    override suspend fun get(key: String): Result<String> = runCatching {
        val value = storage.getItem(key) ?: return Result.failure(Throwable())
        return Result.success(value)
    }

}