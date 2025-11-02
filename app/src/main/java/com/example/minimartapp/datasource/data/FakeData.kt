package com.example.minimartapp.datasource.data

import com.example.minimartapp.datasource.requests.LoginRequest
import com.example.minimartapp.datasource.responses.LoginResponse
import com.example.minimartapp.datasource.responses.UserDataResponse
import kotlin.random.Random

fun getUserData(loginRequest: LoginRequest): LoginResponse {
    val randomValues = Random.nextInt(0, 100)

    return if (randomValues <= 50) {

        if (loginRequest.user == "Juan") {
            LoginResponse(
                status = 200,
                data = UserDataResponse(
                    userName = "Juan",
                    phone = "3315421578",
                    rol = "admin"
                )
            )
        } else {
            LoginResponse(
                status = 200,
                data = UserDataResponse(
                    userName = "Juan",
                    phone = "3315421588",
                    rol = "cajero"
                )
            )
        }
    } else {
        LoginResponse(
            status = 401,
            codeError = 1001,
            data = UserDataResponse(
                userName = "",
                phone = "",
                rol = ""
            )
        )
    }
}