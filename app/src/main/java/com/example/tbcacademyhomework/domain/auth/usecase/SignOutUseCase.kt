package com.example.tbcacademyhomework.domain.auth.usecase

import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.logout()
}