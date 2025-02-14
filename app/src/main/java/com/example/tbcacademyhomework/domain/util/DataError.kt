package com.example.tbcacademyhomework.domain.util

sealed interface DataError : Error {
    data class Literal(val error: String?) : DataError
    enum class DynamicError : DataError {
        UNKNOWN,
    }
}