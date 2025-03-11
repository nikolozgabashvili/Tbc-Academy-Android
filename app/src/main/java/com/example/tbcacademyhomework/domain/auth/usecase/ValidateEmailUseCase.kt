package com.example.tbcacademyhomework.domain.auth.usecase

import javax.inject.Inject

interface ValidateEmailUseCase {
    operator fun invoke(email: String): Boolean
}

class ValidateEmailUseCaseImpl @Inject constructor() : ValidateEmailUseCase {
    override fun invoke(email: String): Boolean {
        return email.matches(Regex("^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$"))
    }
}