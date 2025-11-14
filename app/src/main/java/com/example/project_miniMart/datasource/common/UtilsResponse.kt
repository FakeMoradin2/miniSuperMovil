package com.example.project_miniMart.datasource.common


import com.example.project_miniMart.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> makeNetWorkCall(
    call: suspend () -> T
): ResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ResponseStatus.Success(call())
    }catch (e: Exception){
        val errorMessage = R.string.unknow_host_exepcion
        ResponseStatus.Error(errorMessage)
    }
}