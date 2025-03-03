package com.example.tbcacademyhomework.domain.auth.usecase

import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String) = authRepository.login(email, password)
}