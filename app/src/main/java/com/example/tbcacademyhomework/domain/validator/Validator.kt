package com.example.tbcacademyhomework.domain.validator

interface Validator {
    fun isValid(input: String): Boolean
}