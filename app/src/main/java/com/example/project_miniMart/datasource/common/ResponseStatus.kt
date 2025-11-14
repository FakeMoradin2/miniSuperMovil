package com.example.project_miniMart.datasource.common

sealed class ResponseStatus<T> {
    class Success<T>(val data: T) : ResponseStatus<T>()
    class Error<T>(val message: Int): ResponseStatus<T>()
}