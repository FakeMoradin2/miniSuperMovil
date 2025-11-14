package com.example.project_miniMart.datasource.network.responses

data class ProductApi(
    val id: Int,
    val name: String,
    val price: Double,
    var stock: Int,
    val category: String
)