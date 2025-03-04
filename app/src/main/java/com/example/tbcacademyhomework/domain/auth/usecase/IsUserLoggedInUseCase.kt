package com.example.tbcacademyhomework.domain.auth.usecase

import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.isUserLoggedIn()
}