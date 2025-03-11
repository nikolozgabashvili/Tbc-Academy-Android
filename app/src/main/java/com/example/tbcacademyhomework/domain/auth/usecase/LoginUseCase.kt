package com.example.tbcacademyhomework.domain.auth.usecase

import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.LoginResponseDomain
import com.example.tbcacademyhomework.domain.auth.repository.LoginRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LoginUseCase {
    suspend operator fun invoke(params: AuthUser): Flow<Resource<LoginResponseDomain, DataError>>

}

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginUseCase {

    override suspend fun invoke(params: AuthUser): Flow<Resource<LoginResponseDomain, DataError>> {
        return loginRepository.loginUser(params)
    }
}