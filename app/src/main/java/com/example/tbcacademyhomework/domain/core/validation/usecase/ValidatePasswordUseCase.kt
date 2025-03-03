package com.example.tbcacademyhomework.domain.core.validation.usecase

import com.example.tbcacademyhomework.domain.core.validation.UserDataValidator
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(
    private val userDataValidator: UserDataValidator
) {
    operator fun invoke(password: String) = userDataValidator.validatePassword(password)
}