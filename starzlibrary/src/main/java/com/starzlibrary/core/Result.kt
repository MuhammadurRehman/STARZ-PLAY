package com.starzlibrary.core

sealed class Result <out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val error: T) : Result<T>()
}