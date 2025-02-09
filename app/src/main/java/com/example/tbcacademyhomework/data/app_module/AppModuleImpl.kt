package com.example.tbcacademyhomework.data.app_module

import android.content.Context
import com.example.tbcacademyhomework.data.auth.repository.AuthRepositoryImpl
import com.example.tbcacademyhomework.data.local.UserPrefsDataSourceImpl
import com.example.tbcacademyhomework.data.network.retrofit.RetrofitProvider
import com.example.tbcacademyhomework.data.users.repository.UsersRepositoryImpl
import com.example.tbcacademyhomework.domain.app_module.AppModule
import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.local.UserPrefsDataSource
import com.example.tbcacademyhomework.domain.users.repository.UsersRepository
import com.example.tbcacademyhomework.presentation.validation.EmailValidator
import com.example.tbcacademyhomework.presentation.validation.InputValidator

class AppModuleImpl(
    private val context: Context
) : AppModule {
    override val usersRepository: UsersRepository by lazy {
        UsersRepositoryImpl(RetrofitProvider.usersApiService)
    }

    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(RetrofitProvider.authApiService)
    }

    override val userPrefsDataSource: UserPrefsDataSource by lazy {
        UserPrefsDataSourceImpl(context)
    }
    override val emailValidator: EmailValidator by lazy {
        EmailValidator()
    }
    override val inputValidator: InputValidator by lazy {
        InputValidator()
    }
}