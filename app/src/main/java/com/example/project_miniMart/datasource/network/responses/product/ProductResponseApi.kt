package com.example.project_miniMart.datasource.network.responses.product

import com.google.gson.annotations.SerializedName

data class ProductResponseApi(
    @SerializedName ("success") val success: Boolean,
    @SerializedName ("total") val total: Int,
    @SerializedName ("data" ) val data : List<ProductModelResponse>? = null,
    @SerializedName ("error") val error : String? = null
)
