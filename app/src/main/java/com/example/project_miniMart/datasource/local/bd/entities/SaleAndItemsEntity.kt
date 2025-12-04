package com.example.project_miniMart.datasource.local.bd.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.project_miniMart.utils.SALE_ENTITY

@Entity(tableName = SALE_ENTITY)
data class SaleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val folio: String,
    val seller : String,
    val total: String,
    val createAt: String
)

@Entity(
    tableName = "sale_items",
    foreignKeys = [
        ForeignKey(
            entity = SaleEntity::class,
            parentColumns = ["id"],            // ← tu PK real
            childColumns = ["saleOwnerId"],    // ← FK correcta
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("saleOwnerId")]
)
data class SaleItemEntity(
    @PrimaryKey(autoGenerate = true)
    val itemId: Long = 0,

    val saleOwnerId: Int,   // ← Debe coincidir con el tipo de la PK de SaleEntity
    val nameProducts: String,
    val category: String,
    val quantity: Int,
    val price: Double,
    val subtotal: Double
)
