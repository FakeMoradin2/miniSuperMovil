package com.example.project_miniMart.utils


import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.common.ResponseStatus
import com.example.project_miniMart.widgets.loader.DsLoaderView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> handleRequest(
    call: suspend () -> ResponseStatus<T>,
    onSuccess: suspend (T) -> Unit,
    onError: suspend (Int) -> Unit
) {
    try {
        val result = withContext(Dispatchers.IO) { call() }
        DsLoaderView.dismissLoader()
        when (result) {
            is ResponseStatus.Success -> {
                onSuccess.invoke(result.data)
            }

            is ResponseStatus.Error -> {
                onError.invoke(result.message)
            }

        }
    } catch (e: Exception) {
        DsLoaderView.dismissLoader()
        onError.invoke(R.string.handleRequest_error)
    }
}