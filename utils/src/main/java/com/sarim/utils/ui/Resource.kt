package com.sarim.utils.ui

sealed interface Resource<T> {
    data class Success<T>(
        val data: T,
    ) : Resource<T>

    data class Error<T>(
        val message: MessageType,
    ) : Resource<T>
}
