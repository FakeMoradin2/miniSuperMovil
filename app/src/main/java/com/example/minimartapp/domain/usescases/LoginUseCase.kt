package com.example.minimartapp.domain.usescases

import com.example.minimartapp.datasource.comnom.ResponseStatus
import com.example.minimartapp.datasource.requests.LoginRequest
import com.example.minimartapp.domain.models.UserDataDomain
import com.example.minimartapp.domain.repositories.LoginTask
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginTask: LoginTask) {

    suspend operator fun invoke (loginRequest: LoginRequest): ResponseStatus<UserDataDomain>{
        return loginTask.fetchLogin(loginRequest)
    }

}