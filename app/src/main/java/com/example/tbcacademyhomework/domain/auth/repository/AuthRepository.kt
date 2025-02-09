package com.example.tbcacademyhomework.domain.auth.repository


import com.example.tbcacademyhomework.domain.auth.models.AuthResponse
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginUser(authUser: AuthUser): Flow<Resource<AuthResponse, DataError>>
    suspend fun registerUser(authUser: AuthUser): Flow<Resource<AuthResponse, DataError>>
}