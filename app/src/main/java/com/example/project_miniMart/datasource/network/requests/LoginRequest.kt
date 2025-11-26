package com.example.project_miniMart.datasource.network.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("nombre_usuario") val user: String,
    @SerializedName("password") val password: String
)
