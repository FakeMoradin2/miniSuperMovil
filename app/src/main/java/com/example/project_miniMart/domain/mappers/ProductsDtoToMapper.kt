package com.example.project_miniMart.domain.mappers

import com.example.project_miniMart.datasource.network.responses.ProductApi
import com.example.project_miniMart.domain.models.ProductDomain

abstract class ProductsDtoToMapper {
    companion object{
        private fun fromDtoToDomain(productApi: ProductApi): ProductDomain {
            return ProductDomain(
                id = productApi.id,
                name = productApi.name,
                price = productApi.price,
                stock = productApi.stock,
                category = productApi.category
            )
        }

        fun fromDtoToDomainList(response: List<ProductApi>): List<ProductDomain> {
            return response.map {
                fromDtoToDomain(it)
            }
        }
    }
}