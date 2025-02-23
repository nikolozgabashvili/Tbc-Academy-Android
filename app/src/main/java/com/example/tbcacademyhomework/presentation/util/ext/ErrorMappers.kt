package com.example.tbcacademyhomework.presentation.util.ext

import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.presentation.util.GenericString

fun DataError.toGenericString(): GenericString {
    return when (this) {
        DataError.DynamicError.SERIALIZATION_ERROR -> GenericString.Resource(R.string.serialization_error)
        DataError.DynamicError.UNKNOWN -> GenericString.Resource(R.string.unknowr_error)
        DataError.DynamicError.UNKNOWN_HOST -> GenericString.Resource(R.string.unreachable_host_error)
        is DataError.Literal -> GenericString.Literal(error)
    }
}