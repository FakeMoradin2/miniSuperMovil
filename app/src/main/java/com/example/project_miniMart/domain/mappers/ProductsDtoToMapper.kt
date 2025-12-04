package com.example.project_miniMart.domain.mappers

import com.example.project_miniMart.datasource.network.responses.ProductApi
import com.example.project_miniMart.datasource.network.responses.product.ProductModelResponse
import com.example.project_miniMart.domain.models.ProductDomain

abstract class ProductsDtoToMapper {
    companion object{
        private fun fromDtoToDomain(productApi: ProductModelResponse): ProductDomain {
            return ProductDomain(
                id = productApi.id,
                name = productApi.productName,
                price = productApi.price,
                stock = productApi.stock,
                category = productApi.category,
                image = productApi.image
            )
        }

        fun fromDtoToDomainList(response: List<ProductModelResponse>? ): List<ProductDomain> {
            return response?.map {
                fromDtoToDomain(it)
            } ?: emptyList()
        }
    }
}