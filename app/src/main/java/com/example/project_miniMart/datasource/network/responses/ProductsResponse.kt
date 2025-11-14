package com.example.project_miniMart.datasource.network.responses

data class ProductsResponse (
    val status: Int,
    val codeError: Int? = null,
    val data: List<ProductApi>
)

