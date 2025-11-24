package com.example.project_miniMart.datasource.local.bd.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.project_miniMart.datasource.local.bd.entities.GroupShopping
import com.example.project_miniMart.datasource.local.bd.entities.ShoppingEntity

@Dao
interface ShoppingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shoppingEntity: ShoppingEntity): Long


    @Query("""
        SELECT nameProducts, 
               SUM(price) as totalPrice, 
               COUNT(*) as totalStock, 
               category
        FROM ShoppingEntity
        GROUP BY nameProducts, category
    """)
    suspend fun getGroupedProducts(): List<GroupShopping>

    @Update
    fun update(shoppingEntity: ShoppingEntity)

    @Query("DELETE FROM ShoppingEntity WHERE nameProducts = :productName AND category = :category")
    suspend fun deleteGroup(productName: String, category: String): Int

    @Query("DELETE FROM ShoppingEntity")
    suspend fun deleteAll(): Int
}