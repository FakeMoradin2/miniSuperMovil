package com.example.project_miniMart.utils

import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.common.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Función genérica para manejar llamadas de red.
 *
 * @param call La función suspendida que realiza la llamada de red.
 * @param onSuccess La función a ejecutar en caso de éxito.
 * @param onError La función a ejecutar en caso de error.
 * @param onLoading La función a ejecutar mientras la llamada está en progreso.
 */
suspend fun <T> handleRequest(
    call: suspend () -> ResponseStatus<T>,
    onSuccess: suspend (T) -> Unit,
    onError: suspend (Int) -> Unit,
    onLoading: suspend () -> Unit = {}
) {
    try {
        onLoading.invoke()
        val result = withContext(Dispatchers.IO) { call() }
        when (result) {
            is ResponseStatus.Success -> {
                onSuccess.invoke(result.data)
            }

            is ResponseStatus.Error -> {
                onError.invoke(result.message)
            }
        }
    } catch (e: Exception) {
        onError.invoke(R.string.handleRequest_error)
    }
}
