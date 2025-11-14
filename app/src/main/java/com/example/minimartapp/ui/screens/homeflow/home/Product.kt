package com.example.minimartapp.ui.screens.homeflow.home

data class Product(
        val id: Int,
        val name: String,
        val price: String,
        var stock: Int, // var para poder modificar el stock
        val imageRes: Int,
        val category: String = "All"
)