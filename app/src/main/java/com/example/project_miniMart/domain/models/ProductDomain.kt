package com.example.project_miniMart.domain.models

data class ProductDomain(
    val id: Int,
    val name: String,
    val price: Double,
    var stock: Int,
    val category: String
)