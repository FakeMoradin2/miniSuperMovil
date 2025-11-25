package com.example.project_miniMart.domain.repositories


import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.common.ResponseStatus
import com.example.project_miniMart.datasource.common.makeNetWorkCall
import com.example.project_miniMart.datasource.network.data.ApiContract
import com.example.project_miniMart.datasource.network.requests.LoginRequest
import com.example.project_miniMart.datasource.network.requests.RegisterRequest
import com.example.project_miniMart.domain.mappers.UsersDtoToMapper.Companion.fromDtoToDomain
import com.example.project_miniMart.domain.models.UserDataDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginTask {
    suspend fun fetchLogin(loginRequest: LoginRequest): ResponseStatus<UserDataDomain>
    suspend fun fetchRegister(registerRequest: RegisterRequest): ResponseStatus<UserDataDomain>
    suspend fun recoveryPassword(email: String): ResponseStatus<Int>
}

class LoginRepository @Inject constructor(private val apiContract: ApiContract) : LoginTask {

    //--------------- LOGIN

    override suspend fun fetchLogin(loginRequest: LoginRequest): ResponseStatus<UserDataDomain> {
        return withContext(Dispatchers.IO) {
            val loginResponse = getLoginDeferred(loginRequest)

            if (loginResponse is ResponseStatus.Success) {
                if (loginResponse.data.userName.isNotEmpty()) {
                    loginResponse
                } else {
                    ResponseStatus.Error(R.string.error_exepcion)
                }
            } else {
                loginResponse
            }
        }
    }



    private suspend fun getLoginDeferred(loginRequest: LoginRequest): ResponseStatus<UserDataDomain> =
        makeNetWorkCall {
            val response = apiContract.fetchLogin(loginRequest)
            fromDtoToDomain(response.data)
        }


    //--------------- REGISTER

    override suspend fun fetchRegister(registerRequest: RegisterRequest): ResponseStatus<UserDataDomain> {
        return withContext(Dispatchers.IO) {
            val registerResponse = registerDeferred(registerRequest)


            if (registerResponse is ResponseStatus.Success) {
                if (registerResponse.data.userName != "") {
                    registerResponse
                } else
                    ResponseStatus.Error(R.string.register_error)
            } else {
                registerResponse
            }
        }
    }


    private suspend fun registerDeferred(registerRequest: RegisterRequest): ResponseStatus<UserDataDomain> =
        makeNetWorkCall {
            val response = apiContract.requestRegister(registerRequest)
            fromDtoToDomain(response.dataResponse)
        }

    //--------------- RECOVERY

    override suspend fun recoveryPassword(email: String): ResponseStatus<Int> {
        return withContext(Dispatchers.IO) {
            val recoveryResponse = recoveryDeferred(email)

            if (recoveryResponse is ResponseStatus.Success) {
                if (recoveryResponse.data == 200) {
                    recoveryResponse
                } else
                    ResponseStatus.Error(R.string.error_recovery)
            } else {
                recoveryResponse
            }

        }
    }

    private suspend fun recoveryDeferred(email: String): ResponseStatus<Int> =
        makeNetWorkCall {
            val response = apiContract.recoveryPassword(email)
            response
        }

}