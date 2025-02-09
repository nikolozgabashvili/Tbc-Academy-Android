package com.example.tbcacademyhomework.domain.app_module

import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.local.UserPrefsDataSource
import com.example.tbcacademyhomework.domain.users.repository.UsersRepository
import com.example.tbcacademyhomework.presentation.validation.EmailValidator
import com.example.tbcacademyhomework.presentation.validation.InputValidator

interface AppModule {
    val usersRepository: UsersRepository
    val authRepository: AuthRepository
    val userPrefsDataSource: UserPrefsDataSource
    val emailValidator: EmailValidator
    val inputValidator: InputValidator
}