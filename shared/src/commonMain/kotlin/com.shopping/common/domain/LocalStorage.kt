package com.shopping.common.domain

interface LocalStorage {

    suspend fun put(key: String, value: String): Result<Unit>

    suspend fun get(key: String): Result<String>

}