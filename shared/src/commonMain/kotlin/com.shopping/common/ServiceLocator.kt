package com.shopping.common

import com.shopping.common.client.AuthClientImpl
import com.shopping.common.client.CustomerClientImpl
import com.shopping.common.client.OrderClientImpl
import com.shopping.common.client.ProductClientImpl
import com.shopping.common.domain.LocalStorage
import com.shopping.common.domain.client.AuthClient
import com.shopping.common.domain.client.CustomerClient
import com.shopping.common.domain.client.OrderClient
import com.shopping.common.domain.client.ProductClient
import com.shopping.common.domain.repository.CustomerRepository
import com.shopping.common.domain.repository.OrderRepository
import com.shopping.common.domain.repository.ProductRepository
import com.shopping.common.repository.CustomerRepositoryImpl
import com.shopping.common.repository.OrderRepositoryImpl
import com.shopping.common.repository.ProductRepositoryImpl
import com.shopping.common.viewModel.CustomerViewModel
import com.shopping.common.viewModel.OrderViewModel
import com.shopping.common.viewModel.ProductViewModel
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import kotlinx.coroutines.flow.Flow

class RefreshToken {

    companion object Feature : HttpClientFeature<Unit, RefreshToken> {
        override val key: AttributeKey<RefreshToken>
            get() = AttributeKey("RefreshToken")

        override fun install(feature: RefreshToken, scope: HttpClient) {
            scope.responsePipeline.intercept(HttpResponsePipeline.After) {
                val call = context

                if (isSignedIn && call.response.isUnauthorized) {

                    ServiceLocator.Client.authClient.refreshToken(refreshToken)
                        .fold(
                            onSuccess = { ServiceLocator.localStorage.putTokenResponse(it) },
                            onFailure = { signOut() }
                        )

                    scope.sendPipeline.intercept(HttpSendPipeline.Before) {
                        val currentRequest = context
                        val currentResponse = scope.request<HttpResponse>(currentRequest)
                        proceedWith(currentResponse)
                    }

                }
            }
        }

        override fun prepare(block: Unit.() -> Unit): RefreshToken = RefreshToken()

    }
}

private class ComposeLocalStorage: LocalStorage {
    override suspend fun get(key: String): Result<Flow<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun put(key: String, value: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun remove(key: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun clear(): Result<Unit> {
        TODO("Not yet implemented")
    }

}

object ServiceLocator {

    internal /* lateinit */ var localStorage: LocalStorage
        private set

    init {
        localStorage = ComposeLocalStorage()
    }

    operator fun invoke(localStorage: LocalStorage) {
        this.localStorage = localStorage
    }

    internal object Client {

        private val client by lazy {

            HttpClient {

                defaultRequest {
                    expectSuccess = false
                    host = "heroku.app"
//                    if (isSignedIn) authHeader(accessToken)
                }

                Json {
                    serializer = KotlinxSerializer()
                }

                Logging {
                    level = LogLevel.ALL
                }

//                install(RefreshToken)
            }

        }

        val authClient: AuthClient by lazy {
            AuthClientImpl(client)
        }

        val customerClient: CustomerClient by lazy {
            CustomerClientImpl(client)
        }

        val productClient: ProductClient by lazy {
            ProductClientImpl(client)
        }

        val orderClient: OrderClient by lazy {
            OrderClientImpl(client)
        }

    }

    internal object Repository {

        val customerRepository: CustomerRepository by lazy {
            CustomerRepositoryImpl(localStorage, Client.authClient, Client.customerClient)
        }

        val productRepository: ProductRepository by lazy {
            ProductRepositoryImpl(Client.productClient)
        }

        val orderRepository: OrderRepository by lazy {
            OrderRepositoryImpl(Client.orderClient)
        }
    }

    object ViewModel {
        val customerViewModel: CustomerViewModel by lazy {
            CustomerViewModel(Repository.customerRepository)
        }
        val productViewModel: ProductViewModel by lazy {
            ProductViewModel(Repository.productRepository)
        }
        val orderViewModel: OrderViewModel by lazy {
            OrderViewModel(Repository.orderRepository)
        }
    }
}