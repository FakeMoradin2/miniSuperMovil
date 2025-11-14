package com.example.project_miniMart.domain.mappers

import com.example.project_miniMart.datasource.network.responses.UserDataResponse
import com.example.project_miniMart.domain.models.UserDataDomain

abstract class UsersDtoToMapper {
    companion object{
        fun fromDtoToDomain(userDataResponse: UserDataResponse): UserDataDomain{
            return UserDataDomain(
                userName = userDataResponse.userName,
                workstation = userDataResponse.workstation,
                email = userDataResponse.email,
                age = userDataResponse.age,
                isPrincipal = userDataResponse.isPrincipal,
                phone = userDataResponse.phone
            )
        }
    }
}