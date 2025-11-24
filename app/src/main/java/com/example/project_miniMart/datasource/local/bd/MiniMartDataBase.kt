package com.example.project_miniMart.datasource.local.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project_miniMart.datasource.local.bd.dao.SaleDao
import com.example.project_miniMart.datasource.local.bd.dao.ShoppingDAO
import com.example.project_miniMart.datasource.local.bd.entities.SaleEntity
import com.example.project_miniMart.datasource.local.bd.entities.SaleItemEntity
import com.example.project_miniMart.datasource.local.bd.entities.ShoppingEntity

@Database(
    entities = [
        ShoppingEntity::class,
        SaleEntity::class,
        SaleItemEntity::class],
    version = 2,
    exportSchema = true
)
abstract class MiniMartDataBase : RoomDatabase() {
    abstract fun shoppingDAO(): ShoppingDAO
    abstract fun saleDAO(): SaleDao
}