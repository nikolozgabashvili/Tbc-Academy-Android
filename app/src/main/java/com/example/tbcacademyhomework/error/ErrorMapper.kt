package com.example.tbcacademyhomework.error

import android.content.Context
import com.example.tbcacademyhomework.R

fun UserValidationError.getErrorString(context:Context):String{
    return when(this){
        UserValidationError.EMPTY_FIRST_NAME -> context.getString(R.string.empty_first_name_error)
        UserValidationError.EMPTY_LAST_NAME -> context.getString(R.string.empty_last_name_error)
        UserValidationError.INVALID_EMAIL -> context.getString(R.string.invalid_email_error)
    }
}