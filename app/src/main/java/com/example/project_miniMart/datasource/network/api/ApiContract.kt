package com.example.project_miniMart.datasource.network.api

import com.example.project_miniMart.datasource.network.requests.LoginRequest
import com.example.project_miniMart.datasource.network.requests.RegisterRequestApi
import com.example.project_miniMart.datasource.network.responses.RegisterDataResponse
import com.example.project_miniMart.datasource.network.responses.login.LoginResponseApi
import com.example.project_miniMart.datasource.network.responses.product.ProductResponseApi
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiContract {

    @POST("auth/login.php")
    suspend fun fetchLogin(
        @Body request: LoginRequest
    ): LoginResponseApi

    @POST("auth/register.php")
    suspend fun fetchRegister(
        @Body request: RegisterRequestApi
    ): RegisterDataResponse

    @GET("productos/listar.php")
    suspend fun fetchProducts(): ProductResponseApi


}