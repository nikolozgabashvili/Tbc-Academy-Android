package com.example.tbcacademyhomework.domain.auth.usecase

import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.RegisterResponseDomain
import com.example.tbcacademyhomework.domain.auth.repository.RegisterRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface RegisterUseCase {
    suspend operator fun invoke(params: AuthUser): Flow<Resource<RegisterResponseDomain, DataError>>
}


class RegisterUseCaseImpl @Inject constructor(
    private val registerRepository: RegisterRepository
) : RegisterUseCase {
    override suspend fun invoke(params: AuthUser): Flow<Resource<RegisterResponseDomain, DataError>> {
        return registerRepository.registerUser(params)
    }
}