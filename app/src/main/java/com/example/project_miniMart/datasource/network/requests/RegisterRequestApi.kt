package com.example.project_miniMart.datasource.network.requests

import com.google.gson.annotations.SerializedName

data class RegisterRequestApi(
    @SerializedName ("nombre_usuario") val userName: String,
    @SerializedName ("password") val password: String,
    @SerializedName ("telefono") val phone: String,
    @SerializedName ("rol") val rol: String,
)
