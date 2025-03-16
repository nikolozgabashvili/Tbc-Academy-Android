package com.example.tbcacademyhomework.domain.auth.usecase

import javax.inject.Inject

interface ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean


}

class ValidatePasswordUseCaseImpl @Inject constructor() : ValidatePasswordUseCase {
    override fun invoke(password: String): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
    }
    companion object{
        const val MIN_PASSWORD_LENGTH = 3
    }

}