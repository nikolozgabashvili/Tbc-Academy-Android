package com.example.tbcacademyhomework.domain.util

sealed interface NetworkError : Error {
    data class StringError(val error: String?) : NetworkError
    enum class DataError:NetworkError{
        NETWORK_ERROR,
        SERIALIZATION_ERROR,
        UNKNOWN_ERROR
    }
}