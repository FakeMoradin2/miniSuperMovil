package com.example.project_miniMart.domain.repositories


import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.common.ResponseStatus
import com.example.project_miniMart.datasource.common.makeNetWorkCall
import com.example.project_miniMart.datasource.network.api.ApiContract
import com.example.project_miniMart.datasource.network.data.FakeContract
import com.example.project_miniMart.datasource.network.requests.LoginRequest
import com.example.project_miniMart.datasource.network.requests.RegisterRequest
import com.example.project_miniMart.datasource.network.requests.RegisterRequestApi
import com.example.project_miniMart.domain.mappers.RegisterDtoToMapper.Companion.registerFromDtoToDomain
import com.example.project_miniMart.domain.mappers.UsersDtoToMapper.Companion.fromDtoToDomain
import com.example.project_miniMart.domain.models.RegisterModelDomain
import com.example.project_miniMart.domain.models.UserDataDomain
import com.example.project_miniMart.domain.models.UserModelDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginTask {
    suspend fun fetchLogin(loginRequest: LoginRequest): ResponseStatus<UserModelDomain>
    suspend fun fetchRegister(registerRequest: RegisterRequestApi): ResponseStatus<RegisterModelDomain>
    suspend fun recoveryPassword(email: String): ResponseStatus<Int>
}

class LoginRepository @Inject constructor(
    private val fakeContract: FakeContract,
    private val apiContract: ApiContract
) : LoginTask {

    //--------------- LOGIN

    override suspend fun fetchLogin(loginRequest: LoginRequest): ResponseStatus<UserModelDomain> {
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


    private suspend fun getLoginDeferred(loginRequest: LoginRequest): ResponseStatus<UserModelDomain> =
        makeNetWorkCall {
            val response = apiContract.fetchLogin(loginRequest)
            fromDtoToDomain(response)
        }


    //--------------- REGISTER

    override suspend fun fetchRegister(registerRequest: RegisterRequestApi): ResponseStatus<RegisterModelDomain> {
        return withContext(Dispatchers.IO) {
            val registerResponse = registerDeferred(registerRequest)


            if (registerResponse is ResponseStatus.Success) {
                if (registerResponse.data.state) {
                    registerResponse
                } else
                    ResponseStatus.Error(R.string.register_error)
            } else {
                registerResponse
            }
        }
    }


    private suspend fun registerDeferred(registerRequest: RegisterRequestApi): ResponseStatus<RegisterModelDomain> =
        makeNetWorkCall {
            val response = apiContract.fetchRegister(registerRequest)
            registerFromDtoToDomain (response)
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
            val response = fakeContract.recoveryPassword(email)
            response
        }

}