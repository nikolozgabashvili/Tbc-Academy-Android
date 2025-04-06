package com.example.tbcacademyhomework.domain.auth.usecase

import javax.inject.Inject

interface ValidateEmailUseCase {
    operator fun invoke(email: String): Boolean
}

class ValidateEmailUseCaseImpl @Inject constructor() : ValidateEmailUseCase {
    override fun invoke(email: String): Boolean {
        return email.matches(Regex(
            ("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
        ))
    }
}