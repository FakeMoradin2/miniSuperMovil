package com.example.project_miniMart.datasource.network.responses


data class LoginResponse(
    val status: Int,
    val codeError: Int? = null,
    val data: UserDataResponse
)

