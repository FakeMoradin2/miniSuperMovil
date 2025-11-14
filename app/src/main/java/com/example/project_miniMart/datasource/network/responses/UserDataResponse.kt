package com.example.project_miniMart.datasource.network.responses

data class UserDataResponse(
    val userName: String,
    val workstation: String,
    val email: String,
    val age: Int,
    val isPrincipal: Boolean,
    val phone: String
)
