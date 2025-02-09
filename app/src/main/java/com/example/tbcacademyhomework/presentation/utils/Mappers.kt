package com.example.tbcacademyhomework.presentation.utils

import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.utils.DataError

fun DataError.toGenericString(): GenericString {
    return when (val dataError = this) {
        is DataError.Literal -> GenericString.Literal(dataError.error)
        is DataError.DynamicError -> dataError.toGenericString()
    }
}

fun DataError.DynamicError.toGenericString(): GenericString {
    return when (this) {
        DataError.DynamicError.SERIALIZATION_ERROR -> GenericString.Resource(R.string.serialization_error)
        DataError.DynamicError.SERVER_ERROR -> GenericString.Resource(R.string.server_error)
        DataError.DynamicError.UNKNOWN -> GenericString.Resource(R.string.unknown_error)
        DataError.DynamicError.NO_INTERNET -> GenericString.Resource(R.string.no_internet_connection)
    }
}
