package com.example.tbcacademyhomework.domain.auth.usecase

import com.example.tbcacademyhomework.domain.auth.usecase.ValidatePasswordUseCase.Companion.MIN_PASSWORD_LENGTH
import javax.inject.Inject

interface ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean

    companion object{
        const val MIN_PASSWORD_LENGTH = 3
    }
}

class ValidatePasswordUseCaseImpl @Inject constructor() : ValidatePasswordUseCase {
    override fun invoke(password: String): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
    }
}