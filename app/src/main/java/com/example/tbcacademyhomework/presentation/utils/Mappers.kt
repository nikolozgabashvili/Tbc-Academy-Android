package com.example.tbcacademyhomework.presentation.utils

import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.utils.DataError

fun DataError.toGenericString(): GenericStrings {
    return when (val dataError = this) {
        is DataError.Literal -> GenericStrings.Literal(dataError.error)
        is DataError.DynamicError -> dataError.toGenericString()
    }
}

fun DataError.DynamicError.toGenericString(): GenericStrings {
    return when (this) {
        DataError.DynamicError.SERIALIZATION_ERROR -> GenericStrings.Resource(R.string.serialization_error)
        DataError.DynamicError.SERVER_ERROR -> GenericStrings.Resource(R.string.server_error)
        DataError.DynamicError.UNKNOWN -> GenericStrings.Resource(R.string.unknown_error)
        DataError.DynamicError.NO_INTERNET -> GenericStrings.Resource(R.string.no_internet_connection)
    }
}
