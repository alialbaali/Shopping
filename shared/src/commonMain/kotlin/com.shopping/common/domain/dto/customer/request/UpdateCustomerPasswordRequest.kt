package com.shopping.common.domain.dto.customer.request

class UpdateCustomerPasswordRequest(
    private val oldPassword: String,
    private val newPassword: String,
)