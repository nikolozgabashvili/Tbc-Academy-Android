package com.example.tbcacademyhomework

import androidx.annotation.StringRes

sealed class Response(@StringRes val message: Int? = null) {
    class Success : Response()
    class Error(@StringRes message: Int?) : Response(message)
}


fun Response.isSuccess() = this is Response.Success

fun Response.isError() = this is Response.Error
