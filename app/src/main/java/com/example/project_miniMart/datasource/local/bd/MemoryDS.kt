package com.example.project_miniMart.datasource.local.bd

import com.example.project_miniMart.datasource.local.bd.dao.SaleDao
import com.example.project_miniMart.datasource.local.bd.dao.ShoppingDAO
import com.example.project_miniMart.datasource.local.bd.entities.GroupShopping
import com.example.project_miniMart.datasource.local.bd.entities.SaleWithItems
import com.example.project_miniMart.datasource.local.bd.entities.ShoppingEntity
import com.example.project_miniMart.utils.someTime
import jakarta.inject.Inject


class MemoryDS @Inject constructor(
    private val shoppingDAO: ShoppingDAO,
    private val saleDao: SaleDao
) {


    suspend fun insertShoppingCar(shoppingEntity: ShoppingEntity): Long {
        return shoppingDAO.insert(shoppingEntity)
    }

    suspend fun getAllShoppingCar(): List<GroupShopping> {
        return shoppingDAO.getGroupedProducts()
    }

    suspend fun deleteProductCar(productName: String, category: String): Int {
        return shoppingDAO.deleteGroup(productName, category)
    }

    suspend fun deleteAll(): Int {
        return shoppingDAO.deleteAll()
    }

    //sale

    suspend fun insertNewSale(group: List<GroupShopping>, seller: String, total: String, createAt: String): Int {
        return saleDao.insertSaleFromGroupedProducts(
            groups = group,
            seller = seller,
            total = total,
            createAt
        )
    }

    suspend fun getSaleWithItemsById(saleId: Int): SaleWithItems? {
        return saleDao.getSaleWithItemsById(saleId)
    }

    // TODO: obtener la informacion de las ventas

    suspend fun getAllSale(): List<SaleWithItems> {
        return saleDao.getAllSalesWithItems()
    }

}