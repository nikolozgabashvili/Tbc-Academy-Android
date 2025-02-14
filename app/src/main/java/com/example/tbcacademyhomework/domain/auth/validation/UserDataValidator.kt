package com.example.tbcacademyhomework.domain.auth.validation

interface UserDataValidator {
    fun isUserValid(email: String, password: String): Boolean
}