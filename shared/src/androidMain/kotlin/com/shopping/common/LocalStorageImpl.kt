package com.shopping.common

import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.shopping.common.domain.LocalStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.single

actual class LocalStorageImpl(private val storage: DataStore<Preferences>) : LocalStorage {

    override suspend fun get(key: String): Result<Flow<String>> = runCatching {
        storage.data
                .mapNotNull { preferences -> preferences[preferencesKey(key)] }
    }

    override suspend fun put(key: String, value: String): Result<Unit> = runCatching {
        storage.edit { preferences -> preferences[preferencesKey(key)] = value }
    }

    override suspend fun remove(key: String): Result<Unit> = runCatching {
        storage.edit { preferences -> preferences.remove(preferencesKey(key)) }
    }

    override suspend fun clear(): Result<Unit> = runCatching {
        storage.edit { preferences -> preferences.clear() }
    }

}