package com.example.project_miniMart.domain.repositories

import com.example.project_miniMart.datasource.common.ResponseStatus
import com.example.project_miniMart.datasource.network.data.FakeContract
import com.example.project_miniMart.datasource.network.requests.LoginRequest
import com.example.project_miniMart.datasource.network.requests.RegisterRequest
import com.example.project_miniMart.domain.mappers.UsersDtoToMapper.Companion.fromDtoToDomain
import com.example.project_miniMart.domain.models.UserDataDomain
import javax.inject.Inject

/**
 * Interfaz que define las operaciones de autenticación.
 */
interface LoginTask {
    /**
     * Realiza el login de un usuario.
     *
     * @param loginRequest Los datos de login.
     * @return El estado de la respuesta con los datos del usuario.
     */
    suspend fun fetchLogin(loginRequest: LoginRequest): ResponseStatus<UserDataDomain>

    /**
     * Registra un nuevo usuario.
     *
     * @param registerRequest Los datos de registro.
     * @return El estado de la respuesta con los datos del usuario.
     */
    suspend fun fetchRegister(registerRequest: RegisterRequest): ResponseStatus<UserDataDomain>

    /**
     * Inicia el proceso de recuperación de contraseña.
     *
     * @param email El email del usuario.
     * @return El estado de la respuesta con un código de éxito/error.
     */
    suspend fun recoveryPassword(email: String): ResponseStatus<Int>
}

/**
 * Implementación de [LoginTask] que utiliza un contrato falso para obtener los datos.
 *
 * @param fakeContract El contrato de datos falsos.
 */
class LoginRepository @Inject constructor(private val fakeContract: FakeContract) : LoginTask {

    override suspend fun fetchLogin(loginRequest: LoginRequest): ResponseStatus<UserDataDomain> {
        val response = fakeContract.fetchLogin(loginRequest)
        return ResponseStatus.Success(fromDtoToDomain(response.data))
    }

    override suspend fun fetchRegister(registerRequest: RegisterRequest): ResponseStatus<UserDataDomain> {
        val response = fakeContract.requestRegister(registerRequest)
        return ResponseStatus.Success(fromDtoToDomain(response.dataResponse))
    }

    override suspend fun recoveryPassword(email: String): ResponseStatus<Int> {
        return ResponseStatus.Success(fakeContract.recoveryPassword(email))
    }
}
