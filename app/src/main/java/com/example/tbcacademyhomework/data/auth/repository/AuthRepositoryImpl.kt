package com.example.tbcacademyhomework.data.auth.repository

import com.example.tbcacademyhomework.data.mappers.toAuthUserRequest
import com.example.tbcacademyhomework.data.mappers.toDomain
import com.example.tbcacademyhomework.data.network.service.AuthApiService
import com.example.tbcacademyhomework.data.util.HttpRequestHelper
import com.example.tbcacademyhomework.domain.auth.models.AuthResponse
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.utils.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val authApiService: AuthApiService
) : AuthRepository {
    override suspend fun loginUser(authUser: AuthUser): Flow<Resource<AuthResponse, DataError>> {
        return withContext(Dispatchers.IO) {
            HttpRequestHelper.executeCall {
                authApiService.loginUser(authUser.toAuthUserRequest())
            }.map { resource ->
                resource.map {
                    it.toDomain()
                }

            }
        }
    }

    override suspend fun registerUser(authUser: AuthUser): Flow<Resource<AuthResponse, DataError>> {
        return withContext(Dispatchers.IO) {
            HttpRequestHelper.executeCall {
                authApiService.registerUser(authUser.toAuthUserRequest())
            }.map { resource ->
                resource.map {
                    it.toDomain()
                }
            }
        }
    }
}