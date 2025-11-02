package com.example.minimartapp.domain.mappers

import com.example.minimartapp.datasource.responses.UserDataResponse
import com.example.minimartapp.domain.models.UserDataDomain

abstract class UserDtoMapper {
    companion object{

        //DTO data transform object
        fun fromDtoDomain(userDataResponse: UserDataResponse): UserDataDomain{
            return UserDataDomain(
                userName = userDataResponse.userName,
                phone = userDataResponse.phone,
                rol = userDataResponse.rol
            )
        }
    }
}