package com.example.project_miniMart.datasource.network.requests

data class RegisterRequest(
    val userName: String,
    val workstation: String,
    val email: String,
    val age: Int,
    val isPrincipal: Boolean,
    val phone: String
)
