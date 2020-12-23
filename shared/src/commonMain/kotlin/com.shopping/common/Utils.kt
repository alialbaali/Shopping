package com.shopping.common

import com.shopping.common.domain.LocalStorage
import com.shopping.common.domain.dto.customer.response.TokenResponse
import com.shopping.common.domain.model.valueObject.Email
import com.shopping.common.domain.model.valueObject.Password
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

private const val CustomerId = "customer_id"
private const val AccessToken = "access_token"
private const val RefreshTokenKey = "refresh_token"

internal inline val HttpResponse.isSuccess
    get() = status.value in 200..299

internal inline val HttpResponse.isUnauthorized
    get() = status == HttpStatusCode.Unauthorized

internal fun HttpRequestBuilder.authHeader(accessToken: String) = headers.append("Authorization", accessToken)

internal expect val IODispatcher: CoroutineDispatcher

expect class LocalStorageImpl : LocalStorage

inline val LocalDate.Companion.now
    get() = Clock.System.todayAt(TimeZone.currentSystemDefault())

internal typealias DefaultResponse = Map<String, String>

internal typealias ErrorResponse = DefaultResponse

val ErrorResponse.error: String?
    get() = this["error"]

expect class File

expect fun File.toByteArray(): ByteArray

internal suspend inline fun <reified T> HttpResponse.toResult(): Result<T> {
    return if (isSuccess) {
        val response = receive<T>()
        Result.success(response)
    } else {
        val errorResponse = receive<ErrorResponse>()
        Result.failure(Throwable(errorResponse.error))
    }
}

private typealias HttpRequest = HttpClient.(accessToken: String, customerId: String) -> HttpResponse

fun signOut() {}

internal suspend inline fun <reified T> HttpClient.withCustomer(request: HttpRequest): Result<T> {

    val localStorage = ServiceLocator.localStorage

    val authClient = ServiceLocator.Client.authClient

    val httpResponse = with(request(accessToken, customerId)) {
        if (isUnauthorized) {

            val tokenResponse = authClient.refreshToken(refreshToken)
                .onFailure { signOut() }
                .getOrThrow()

            localStorage.putTokenResponse(tokenResponse)

            request(tokenResponse.accessToken, customerId)

        } else {
            this
        }
    }

    return httpResponse.toResult()
}

val isSignedIn: Boolean
    get() = useCoroutine {
        ServiceLocator
            .localStorage
            .get(AccessToken)
            .isSuccess
    }

internal val customerId: String
    get() = useCoroutine {
        ServiceLocator
            .localStorage
            .get(CustomerId)
            .onFailure { signOut() }
            .getOrThrow()
            .single()
    }

internal val accessToken: String
    get() = useCoroutine {
        ServiceLocator
            .localStorage
            .get(AccessToken)
            .onFailure { signOut() }
            .getOrThrow()
            .single()
    }

internal val refreshToken: String
    get() = useCoroutine {
        ServiceLocator
            .localStorage
            .get(RefreshTokenKey)
            .onFailure { signOut() }
            .getOrThrow()
            .single()
    }

@OptIn(ExperimentalCoroutinesApi::class)
internal fun <T> useCoroutine(dispatcher: CoroutineDispatcher = IODispatcher, block: suspend () -> T): T {
    return CoroutineScope(dispatcher)
        .async { block() }
        .also { if (it.isCompleted) it.cancel() } // Might need to remove this block...
        .getCompleted()
}

internal suspend fun LocalStorage.putTokenResponse(
    tokenResponse: TokenResponse,
    dispatcher: CoroutineDispatcher = IODispatcher,
): Unit = withContext(dispatcher) {
    val (customerId, accessToken, refreshToken) = tokenResponse
    put(CustomerId, customerId).getOrThrow()
    put(AccessToken, accessToken).getOrThrow()
    put(RefreshTokenKey, refreshToken).getOrThrow()
}

fun emailValidator(email: String): Boolean = Email.create(email).isSuccess
fun passwordValidator(password: String): Boolean = Password.create(password).isSuccess
fun textValidator(text: String): Boolean = text.isBlank()
