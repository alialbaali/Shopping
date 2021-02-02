package com.shopping.common.domain.client

import com.shopping.common.domain.dto.customer.request.SignUpRequest
import com.shopping.common.domain.dto.customer.request.SignInRequest
import com.shopping.common.domain.dto.customer.response.TokenResponse

internal interface AuthClient {

    suspend fun signUp(signUpRequest: SignUpRequest): Result<TokenResponse>

    suspend fun signIn(signInRequest: SignInRequest): Result<TokenResponse>

    suspend fun refreshToken(refreshToken: String): Result<TokenResponse>

}