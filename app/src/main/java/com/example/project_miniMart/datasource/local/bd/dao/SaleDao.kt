package com.example.project_miniMart.datasource.local.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.project_miniMart.datasource.local.bd.entities.GroupShopping
import com.example.project_miniMart.datasource.local.bd.entities.SaleEntity
import com.example.project_miniMart.datasource.local.bd.entities.SaleItemEntity
import com.example.project_miniMart.datasource.local.bd.entities.SaleWithItems
import com.example.project_miniMart.utils.getCurrentDateFormatted

@Dao
interface SaleDao {

    @Transaction
    @Query("SELECT * FROM SaleEntity")
    suspend fun getAllSalesWithItems(): List<SaleWithItems>

    @Transaction
    @Query("SELECT * FROM SaleEntity WHERE id = :saleId LIMIT 1")
    suspend fun getSaleWithItemsById(saleId: Int): SaleWithItems?

    @Insert
    suspend fun insertSale(sale: SaleEntity): Long

    @Insert
    suspend fun insertSaleItems(items: List<SaleItemEntity>)

    @Transaction
    suspend fun insertSaleFromGroupedProducts(
        groups: List<GroupShopping>,
        seller: String,
        total: String,
        createAt: String
    ): Int {

        val sale = SaleEntity(
            id = 0,
            folio = System.currentTimeMillis().toString(),
            seller = seller,
            total = total,
            createAt = createAt
        )

        val saleId = insertSale(sale).toInt()

        val items = groups.map {
            SaleItemEntity(
                saleOwnerId = saleId,
                nameProducts = it.nameProducts,
                category = it.category,
                quantity = it.totalStock,
                price = it.totalPrice / it.totalStock,
                subtotal = it.totalPrice
            )
        }

        insertSaleItems(items)

        return saleId
    }
}
