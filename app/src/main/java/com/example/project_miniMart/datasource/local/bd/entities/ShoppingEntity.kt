package com.example.project_miniMart.datasource.local.bd.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.project_miniMart.utils.SHOPPING_ENTITY


@Entity(tableName = SHOPPING_ENTITY)
data class ShoppingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nameProducts: String,
    val price: Double,
    val stock: Int,
    val category: String
)