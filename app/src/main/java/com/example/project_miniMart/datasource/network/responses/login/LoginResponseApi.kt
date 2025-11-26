package com.example.project_miniMart.datasource.network.responses.login

import com.google.gson.annotations.SerializedName

data class LoginResponseApi(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("usuario") val data: UserResponseApi? = null
)