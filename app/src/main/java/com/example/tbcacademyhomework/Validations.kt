package com.example.tbcacademyhomework

import android.util.Patterns

fun validateEmail(email: String): Response {
    return if (email.isBlank()) Response.Error(R.string.empty_field_error)
    else if (!Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    ) Response.Error(R.string.invalid_email)
    else Response.Success()
}

fun validateField(name: String): Response {
    return if (name.isBlank()) Response.Error(R.string.empty_field_error)
    else Response.Success()
}

fun validateName(name: String): Response {
    return if (name.isBlank()) Response.Error(R.string.empty_field_error)
    else if (name.any { it.isDigit() }) Response.Error(R.string.invalid_name)
    else Response.Success()
}

