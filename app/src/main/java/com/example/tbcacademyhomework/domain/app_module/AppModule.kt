package com.example.tbcacademyhomework.domain.app_module

import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.connectivity_observer.ConnectivityObserver
import com.example.tbcacademyhomework.domain.local.datastore.UserPrefsDataSource
import com.example.tbcacademyhomework.domain.remote.repository.UsersRemoteSource
import com.example.tbcacademyhomework.domain.validator.Validator

interface AppModule {
    val usersRemoteSource: UsersRemoteSource
    val authRepository: AuthRepository
    val userPrefsDataSource: UserPrefsDataSource
    val emailValidator: Validator
    val inputValidator: Validator
    val connectivityObserver: ConnectivityObserver
}