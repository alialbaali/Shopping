package com.shopping.common

import com.shopping.common.domain.LocalStorage
import org.w3c.dom.Storage

internal actual class LocalStorageImpl(private val storage: Storage) : LocalStorage {

    override suspend fun put(key: String, value: String): Result<Unit> = runCatching {
        storage.setItem(key, value)
    }

    override suspend fun get(key: String): Result<String> = runCatching {
        storage.getItem(key) ?: return Result.failure(Throwable())
    }

    override suspend fun remove(key: String): Result<Unit> = runCatching {
        storage.removeItem(key)
    }

    override suspend fun clear(): Result<Unit> = runCatching {
        storage.clear()
    }

}