package com.example.tbcacademyhomework.validators

import androidx.annotation.StringRes

data class ValidationResult(
    val isError: Boolean = false,
    @StringRes val errorMessage: Int? = null
)