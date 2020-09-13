package com.shopping.common

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.shopping.common.domain.LocalStorage
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single

actual class LocalStorageImpl(private val storage: DataStore<Preferences>) : LocalStorage {

    override suspend fun put(key: String, value: String): Result<Unit> = runCatching {

        storage.edit { preferences ->
            val preferenceKey = preferencesKey<String>(key)
            preferences[preferenceKey] = value
        }

        return Result.success(Unit)
    }

    override suspend fun get(key: String): Result<String> = runCatching {

        val value = storage.data
            .map { preferences ->
                preferences[preferencesKey(key)]
            }
            .single()
            .toString()

        return Result.success(value)
    }
}