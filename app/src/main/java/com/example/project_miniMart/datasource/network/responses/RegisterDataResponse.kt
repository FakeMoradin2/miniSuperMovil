package com.example.project_miniMart.datasource.network.responses

import com.google.gson.annotations.SerializedName

data class RegisterDataResponse(
    @SerializedName ("success") val success: Boolean,
    @SerializedName ("message") val message: String
)
