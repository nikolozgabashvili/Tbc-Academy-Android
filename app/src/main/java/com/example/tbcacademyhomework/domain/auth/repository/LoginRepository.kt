package com.example.tbcacademyhomework.domain.auth.repository

import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.LoginResponseDomain
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginUser(authUser: AuthUser): Flow<Resource<LoginResponseDomain, DataError>>
}