package com.example.tbcacademyhomework.data.auth.repository

import com.example.tbcacademyhomework.data.auth.service.RegisterApiService
import com.example.tbcacademyhomework.data.auth.util.toAuthUserRequest
import com.example.tbcacademyhomework.data.auth.util.toDomain
import com.example.tbcacademyhomework.data.util.HttpRequestHelper
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.RegisterResponseDomain
import com.example.tbcacademyhomework.domain.auth.repository.RegisterRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val httpRequestHelper: HttpRequestHelper,
    private val registerApiService: RegisterApiService
) : RegisterRepository {
    override suspend fun registerUser(authUser: AuthUser): Flow<Resource<RegisterResponseDomain, DataError>> {
        return httpRequestHelper.safeCall {
            registerApiService.registerUser(authUser.toAuthUserRequest())
        }.map { resource ->
            resource.map { dto ->
                dto.toDomain()
            }
        }
    }
}
