package com.shopping.common

import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.shopping.common.domain.LocalStorage
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull

internal actual class LocalStorageImpl(private val storage: DataStore<Preferences>) : LocalStorage {

    override suspend fun put(key: String, value: String): Result<Unit> = runCatching {
        storage.edit { preferences -> preferences[preferencesKey(key)] = value }
    }

    override suspend fun get(key: String): Result<String> = runCatching {
        storage.data
            .map { preferences -> preferences[preferencesKey(key)] }
            .single()
            ?.let { it.toString() } ?: return Result.failure(Throwable())
    }

    override suspend fun remove(key: String): Result<Unit> = runCatching {
        storage.edit { preferences -> preferences.remove(preferencesKey(key)) }
    }

    override suspend fun clear(): Result<Unit> = runCatching {
        storage.edit { preferences -> preferences.clear() }
    }

}