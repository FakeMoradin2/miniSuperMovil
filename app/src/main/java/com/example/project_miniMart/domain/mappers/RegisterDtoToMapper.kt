package com.example.project_miniMart.domain.mappers

import com.example.project_miniMart.datasource.network.responses.RegisterDataResponse
import com.example.project_miniMart.datasource.network.responses.login.LoginResponseApi
import com.example.project_miniMart.domain.models.RegisterModelDomain
import com.example.project_miniMart.domain.models.UserModelDomain


abstract class RegisterDtoToMapper {
    companion object{
        fun registerFromDtoToDomain(registerDataResponse: RegisterDataResponse): RegisterModelDomain {
            return RegisterModelDomain(
                state = registerDataResponse.success,
                message = registerDataResponse.message
            )
        }
    }
}







