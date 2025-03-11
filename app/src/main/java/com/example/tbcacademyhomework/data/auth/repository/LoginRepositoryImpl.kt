package com.example.tbcacademyhomework.data.auth.repository

import com.example.tbcacademyhomework.data.auth.service.LoginApiService
import com.example.tbcacademyhomework.data.auth.util.toAuthUserRequest
import com.example.tbcacademyhomework.data.auth.util.toDomain
import com.example.tbcacademyhomework.data.util.HttpRequestHelper
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.LoginResponseDomain
import com.example.tbcacademyhomework.domain.auth.repository.LoginRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val httpRequestHelper: HttpRequestHelper,
    private val loginApiService: LoginApiService
) : LoginRepository {
    override suspend fun loginUser(authUser: AuthUser): Flow<Resource<LoginResponseDomain, DataError>> {
        return httpRequestHelper.safeCall {
            loginApiService.loginUser(authUser.toAuthUserRequest())
        }.map { resource ->
            resource.map { dto ->
                dto.toDomain()
            }
        }


    }
}
