package com.example.tbcacademyhomework.domain.utils


sealed interface DataError : Error {
    data class Literal(val error: String?) : DataError
    enum class DynamicError : DataError {
        SERIALIZATION_ERROR,
        SERVER_ERROR,
        UNKNOWN,
        NO_INTERNET
    }
}