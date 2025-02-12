package com.example.tbcacademyhomework.data.app_module

import android.content.Context
import com.example.tbcacademyhomework.data.auth.repository.AuthRepositoryImpl
import com.example.tbcacademyhomework.data.local.datastore.UserPrefsDataSourceImpl
import com.example.tbcacademyhomework.data.network.connectivity_observer.NetworkConnectivityObserver
import com.example.tbcacademyhomework.data.network.retrofit.RetrofitProvider
import com.example.tbcacademyhomework.data.remote.repository.UsersRemoteSourceImpl
import com.example.tbcacademyhomework.domain.app_module.AppModule
import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.connectivity_observer.ConnectivityObserver
import com.example.tbcacademyhomework.domain.local.datastore.UserPrefsDataSource
import com.example.tbcacademyhomework.domain.remote.repository.UsersRemoteSource
import com.example.tbcacademyhomework.domain.validator.Validator
import com.example.tbcacademyhomework.presentation.validation.EmailValidator
import com.example.tbcacademyhomework.presentation.validation.InputValidator

class AppModuleImpl(
    private val context: Context
) : AppModule {
    override val usersRemoteSource: UsersRemoteSource by lazy {
        UsersRemoteSourceImpl(RetrofitProvider.usersApiService)
    }

    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(RetrofitProvider.authApiService)
    }

    override val userPrefsDataSource: UserPrefsDataSource by lazy {
        UserPrefsDataSourceImpl(context)
    }
    override val emailValidator: Validator by lazy {
        EmailValidator()
    }
    override val inputValidator: Validator by lazy {
        InputValidator()
    }
    override val connectivityObserver: ConnectivityObserver by lazy {
        NetworkConnectivityObserver(context)
    }
}