package com.example.project_miniMart.datasource.network.responses

data class RegisterResponse(
    val status: Int,
    val codeError: Int? = null,
    val message: String? = null,
    val dataResponse: UserDataResponse
)

