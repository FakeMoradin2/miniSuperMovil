package com.example.project_miniMart.domain.repositories

import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.common.ResponseStatus
import com.example.project_miniMart.datasource.common.makeNetWorkCall
import com.example.project_miniMart.datasource.local.bd.MemoryDS
import com.example.project_miniMart.datasource.local.bd.entities.GroupShopping
import com.example.project_miniMart.datasource.local.bd.entities.SaleWithItems
import com.example.project_miniMart.datasource.local.bd.entities.ShoppingEntity
import com.example.project_miniMart.datasource.network.data.ApiContract
import com.example.project_miniMart.domain.mappers.ProductsDtoToMapper.Companion.fromDtoToDomainList
import com.example.project_miniMart.domain.models.ProductDomain
import com.example.project_miniMart.utils.getCurrentDateFormatted
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.collections.isNotEmpty

interface HomeTask {
    suspend fun getALLSaleList(): ResponseStatus<List<SaleWithItems>>
    suspend fun getSaleWithItemsById(id: Int): ResponseStatus<SaleWithItems?>
    suspend fun fetchAllProducts(): ResponseStatus<List<ProductDomain>>
    suspend fun insertShoppingCar(shoppingEntity: ShoppingEntity): ResponseStatus<Int>
    suspend fun getAllShoppingCar(): ResponseStatus<List<GroupShopping>>
    suspend fun deleteProductShopping(productName: String, category: String): ResponseStatus<Int>
    suspend fun insertNewSale(
        group: List<GroupShopping>,
        seller: String,
        total: String
    ): ResponseStatus<Int>

    suspend fun deleteAllCar(): ResponseStatus<Int>
}

class HomeRepository @Inject constructor(
    private val apiContract: ApiContract,
    private val memoryDS: MemoryDS
) : HomeTask {

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
            val response = apiContract.fetchAllCategories()
            fromDtoToDomainList(response.data)
        }

    // ----------------------------

    override suspend fun insertShoppingCar(shoppingEntity: ShoppingEntity): ResponseStatus<Int> {
        return withContext(Dispatchers.IO) {
            val response = insertProductShoppingCar(shoppingEntity)


            if (response is ResponseStatus.Success) {
                if (response.data != -1L)
                    ResponseStatus.Success(R.string.add_success)
                else
                    ResponseStatus.Error(R.string.add_exepcion)
            } else ResponseStatus.Error(R.string.add_exepcion)
        }
    }

    private suspend fun insertProductShoppingCar(shoppingEntity: ShoppingEntity): ResponseStatus<Long> =
        makeNetWorkCall {
            val response = memoryDS.insertShoppingCar(shoppingEntity)
            response
        }

    // ----------------------------

    override suspend fun getAllShoppingCar(): ResponseStatus<List<GroupShopping>> {
        return withContext(Dispatchers.IO) {
            val response = getAllCar()
            response
        }
    }

    private suspend fun getAllCar(): ResponseStatus<List<GroupShopping>> =
        makeNetWorkCall {
            val response = memoryDS.getAllShoppingCar()
            response
        }

    // ----------------------------

    override suspend fun deleteProductShopping(
        productName: String,
        category: String
    ): ResponseStatus<Int> {
        return withContext(Dispatchers.IO) {
            val response = deleteFromShopping(productName, category)
            if (response is ResponseStatus.Success) {
                if (response.data != 0) {
                    ResponseStatus.Success(R.string.product_deleted_successfully)
                } else ResponseStatus.Error(R.string.unknow_exepcion)
            } else response
        }
    }

    private suspend fun deleteFromShopping(
        productName: String,
        category: String
    ): ResponseStatus<Int> =
        makeNetWorkCall {
            val response = memoryDS.deleteProductCar(productName, category)
            response
        }

    // ----------------------------

    override suspend fun insertNewSale(
        group: List<GroupShopping>,
        seller: String,
        total: String
    ): ResponseStatus<Int> {
        return withContext(Dispatchers.IO) {
            val response = insertNewSaleWithGroup(group, seller, total, getCurrentDateFormatted())
            if (response is ResponseStatus.Success) {
                if (response.data > 0) {
                    response
                } else ResponseStatus.Error(R.string.unknow_exepcion)
            } else response
        }
    }

    private suspend fun insertNewSaleWithGroup(
        group: List<GroupShopping>,
        seller: String,
        total: String,
        createAt: String
    ): ResponseStatus<Int> =
        makeNetWorkCall {
            val response = memoryDS.insertNewSale(group, seller, total, createAt = createAt)
            response
        }

    // ----------------------------
    override suspend fun deleteAllCar(): ResponseStatus<Int> {
        return withContext(Dispatchers.IO) {
            val response = deleteAll()
            response
        }
    }

    private suspend fun deleteAll(): ResponseStatus<Int> =
        makeNetWorkCall {
            val response = memoryDS.deleteAll()
            response
        }

    // ----------------------------

    override suspend fun getSaleWithItemsById(id: Int): ResponseStatus<SaleWithItems?> {
        return withContext(Dispatchers.IO) {
            val response = getSale(id)
            response
        }
    }

    private suspend fun getSale(id: Int): ResponseStatus<SaleWithItems?> =
        makeNetWorkCall {
            val response = memoryDS.getSaleWithItemsById(id)
            response
        }

    //------------------------------------
    override suspend fun getALLSaleList(): ResponseStatus<List<SaleWithItems>> {
        return withContext(Dispatchers.IO) {
            val response = getAllSale()
            response
        }
    }

    private suspend fun getAllSale(): ResponseStatus<List<SaleWithItems>> =
        makeNetWorkCall {
            val response = memoryDS.getAllSale()
            response
        }

}