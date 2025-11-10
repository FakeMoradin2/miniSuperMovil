package com.example.minimartapp.domain.repositories

import com.example.minimartapp.R
import com.example.minimartapp.datasource.comnom.ResponseStatus
import com.example.minimartapp.datasource.comnom.makeNetworkCall
import com.example.minimartapp.datasource.data.fakecontrast
import com.example.minimartapp.datasource.requests.LoginRequest
import com.example.minimartapp.domain.mappers.UserDtoMapper.Companion.fromDtoDomain
import com.example.minimartapp.domain.models.UserDataDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginTask {
    suspend fun fetchLogin(loginRequest: LoginRequest): ResponseStatus<UserDataDomain>

}


class LoginRepository @Inject constructor(private val fakecontrast: fakecontrast) : LoginTask{

    override suspend fun fetchLogin(loginRequest: LoginRequest): ResponseStatus<UserDataDomain> {
        return withContext(Dispatchers.IO){
            val loginDeferred = async { getLoginDeferred(loginRequest) }
            val LoginResponse = loginDeferred.await()
            if (LoginResponse is ResponseStatus.Success){
                if (LoginResponse.data.userName != ""){
                    LoginResponse
                }else{
                    ResponseStatus.Error(R.string.error_exepcion)
                }
            }else{
                LoginResponse
            }

        }
    }

    private suspend fun getLoginDeferred(loginRequest: LoginRequest): ResponseStatus<UserDataDomain> =
        makeNetworkCall {
            val response = fakecontrast.fetchLogin(loginRequest)
            fromDtoDomain(response.data)
        }

}

