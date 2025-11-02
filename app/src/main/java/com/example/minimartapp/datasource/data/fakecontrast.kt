package com.example.minimartapp.datasource.data

import com.example.minimartapp.datasource.requests.LoginRequest
import com.example.minimartapp.datasource.responses.LoginResponse
import com.example.minimartapp.utils.Util
import kotlinx.coroutines.delay
import javax.inject.Inject

class fakecontrast @Inject constructor() {

    suspend fun fetchLogin(loginRequest: LoginRequest): LoginResponse{
        delay(Util.someTime())
        return getUserData(loginRequest)
    }

}