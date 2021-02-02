package com.shopping.common.client

import com.shopping.common.authHeader
import com.shopping.common.domain.client.Endpoint
import com.shopping.common.domain.client.AuthClient
import com.shopping.common.domain.dto.customer.request.SignInRequest
import com.shopping.common.domain.dto.customer.request.SignUpRequest
import com.shopping.common.domain.dto.customer.response.TokenResponse
import io.ktor.client.*
import io.ktor.client.request.*

private const val Auth = "auth"

internal class AuthClientImpl(private val client: HttpClient) : AuthClient {

    override suspend fun signUp(signUpRequest: SignUpRequest): Result<TokenResponse> = client.post("$Auth/new") {
        body = signUpRequest
    }

    override suspend fun signIn(signInRequest: SignInRequest): Result<TokenResponse> = client.post(Endpoint.Auth.SignInRequest) {
        body = signInRequest
    }

    override suspend fun refreshToken(refreshToken: String): Result<TokenResponse> = client.get(Auth) {
        authHeader(refreshToken)
    }

}