package com.example.project_miniMart.domain.repositories

import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.common.ResponseStatus
import com.example.project_miniMart.datasource.common.makeNetWorkCall
import com.example.project_miniMart.datasource.network.data.FakeContract
import com.example.project_miniMart.domain.mappers.ProductsDtoToMapper.Companion.fromDtoToDomainList
import com.example.project_miniMart.domain.models.ProductDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface HomeTask {
    suspend fun fetchAllProducts(): ResponseStatus<List<ProductDomain>>
}

class HomeRepository @Inject constructor(private val fakeContract: FakeContract) : HomeTask {

    override suspend fun fetchAllProducts(): ResponseStatus<List<ProductDomain>> {
        return withContext(Dispatchers.IO) {
            val response = getProductsDeferred()
            if (response is ResponseStatus.Success) {
                if (response.data.isNotEmpty())
                    response
                else
                    ResponseStatus.Error(R.string.error_fetch_products)
            } else response
        }
    }

    private suspend fun getProductsDeferred(): ResponseStatus<List<ProductDomain>> =
        makeNetWorkCall {
            val response = fakeContract.fetchAllProducts()
            fromDtoToDomainList(response.data)
        }
}