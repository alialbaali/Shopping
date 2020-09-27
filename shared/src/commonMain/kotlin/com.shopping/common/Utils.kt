package com.shopping.common

import com.shopping.common.domain.LocalStorage
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

internal inline val HttpResponse.isSuccess
    get() = status.value in 200..300

internal fun HttpRequestBuilder.authHeader(accessToken: String) = headers.append("Authorization", accessToken)

expect val IODispatcher: CoroutineDispatcher

expect class LocalStorageImpl : LocalStorage

inline val LocalDate.Companion.now
    get() = Clock.System.todayAt(TimeZone.currentSystemDefault())