package com.example.tbcacademyhomework.validators

interface Validator {
    fun validate(input:String?):ValidationResult
}