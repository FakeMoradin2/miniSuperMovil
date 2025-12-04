package com.example.project_miniMart.datasource.local.bd.entities

import androidx.room.Embedded
import androidx.room.Relation

data class SaleWithItems(
    @Embedded val sale: SaleEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "saleOwnerId"
    )
    val items: List<SaleItemEntity>
)