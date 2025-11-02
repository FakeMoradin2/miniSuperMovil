package com.example.minimartapp.datasource.comnom


import com.example.minimartapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> makeNetworkCall(
    call: suspend () -> T): ResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ResponseStatus.Success(call())
    }catch (e: Exception){
        val errorMessage = R.string.unknow_host_exepcion
        ResponseStatus.Error(errorMessage)
    }
}