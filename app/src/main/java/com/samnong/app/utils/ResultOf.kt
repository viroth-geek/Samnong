package com.samnong.app.utils

sealed class ResultOf<out T: Any> {
    data class Success<out T : Any>(val data: T) : ResultOf<T>()
    data class Error(val exception: String) : ResultOf<Nothing>()
}