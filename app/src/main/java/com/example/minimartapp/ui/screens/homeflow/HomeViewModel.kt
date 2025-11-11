package com.example.minimartapp.ui.screens.homeflow

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.minimartapp.R
import com.example.minimartapp.ui.screens.homeflow.home.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel

class HomeViewModel @Inject constructor() : ViewModel() {

    private val _products = mutableStateListOf(
        Product(
            1, "Whole Milk", "25", 8, R
                .drawable.ic_logo, "Dairy"
        ),
        Product(2, "Chocolate Cake", "50", 5, R.drawable.ic_logo, "Bakery"),
        // ... más productos
    )

    val products: List<Product> get() = _products

    fun decreaseStock(productId: Int) {
        val index = _products.indexOfFirst { it.id == productId }
        if (index != -1 && _products[index].stock > 0) {
            _products[index] = _products[index].copy(stock = _products[index].stock - 1)
        }
    }
}
