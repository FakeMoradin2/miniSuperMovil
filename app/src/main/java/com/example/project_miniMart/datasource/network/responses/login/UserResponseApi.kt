package com.example.project_miniMart.datasource.network.responses.login

import com.google.gson.annotations.SerializedName

data class UserResponseApi(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre_usuario") val userName: String,
    @SerializedName("rol") val rol: String,
)