package com.shopping.common

import com.shopping.common.domain.LocalStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.w3c.dom.Storage

actual class LocalStorageImpl(private val storage: Storage) : LocalStorage {

    override suspend fun put(key: String, value: String): Result<Unit> = runCatching {
        storage.setItem(key, value)
    }

    override suspend fun get(key: String): Result<Flow<String>> = runCatching {
        flowOf(storage.getItem(key) ?: return Result.failure(Throwable()))
    }

    override suspend fun remove(key: String): Result<Unit> = runCatching {
        storage.removeItem(key)
    }

    override suspend fun clear(): Result<Unit> = runCatching {
        storage.clear()
    }

}