package com.example.project_miniMart.datasource.network.responses.product

import com.google.gson.annotations.SerializedName

data class ProductModelResponse(
    @SerializedName ("producto_id") val id : Int,
    @SerializedName ("nombre_producto") val productName: String,
    @SerializedName ("precio") val price : Double,
    @SerializedName ("stock") val stock : Int,
    @SerializedName ("activo_producto") val active : Int,
    @SerializedName ("image_url") val image: String,
    @SerializedName ("categoria") val category : String,
    @SerializedName ("proveedor") val supplier: String,
)