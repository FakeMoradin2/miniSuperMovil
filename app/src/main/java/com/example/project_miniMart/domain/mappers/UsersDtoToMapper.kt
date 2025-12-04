package com.example.project_miniMart.domain.mappers

import com.example.project_miniMart.datasource.network.responses.UserDataResponse
import com.example.project_miniMart.datasource.network.responses.login.LoginResponseApi
import com.example.project_miniMart.datasource.network.responses.login.UserResponseApi
import com.example.project_miniMart.domain.models.UserDataDomain
import com.example.project_miniMart.domain.models.UserModelDomain

abstract class UsersDtoToMapper {
    companion object{
        fun fromDtoToDomain(userDataResponse: LoginResponseApi): UserModelDomain {
            return UserModelDomain(
                message = userDataResponse.message,
                id = userDataResponse.data?.id ?: 0,
                userName = userDataResponse.data?.userName ?: "",
                rol = userDataResponse.data?.rol ?: ""
            )
        }
    }
}