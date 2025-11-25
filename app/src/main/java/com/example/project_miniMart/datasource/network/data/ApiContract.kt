package com.example.project_miniMart.datasource.network.data

import com.example.project_miniMart.datasource.network.requests.LoginRequest
import com.example.project_miniMart.datasource.network.requests.RegisterRequest
import com.example.project_miniMart.datasource.network.responses.ProductsResponse
import com.example.project_miniMart.datasource.network.responses.LoginResponse
import com.example.project_miniMart.datasource.network.responses.RegisterResponse
import com.example.project_miniMart.utils.someTime
import kotlinx.coroutines.delay
import javax.inject.Inject

// TODO: 1 -> request, 2 -> regresar el response
class ApiContract @Inject constructor() {

    suspend fun fetchLogin(loginRequest: LoginRequest): LoginResponse {
        delay(someTime())
        return getUserData(loginRequest)
    }

    suspend fun requestRegister(registerRequest: RegisterRequest): RegisterResponse {
        delay(someTime())
        return getRegisterRequest(registerRequest)
    }

    suspend fun recoveryPassword(email: String): Int {
        delay(someTime())
        return getRecoveryPasswordRequest(email)
    }

    //----------- home
    suspend fun fetchAllCategories(): ProductsResponse {
        delay(someTime())
        return getAllCategoriesRequest()
    }
}