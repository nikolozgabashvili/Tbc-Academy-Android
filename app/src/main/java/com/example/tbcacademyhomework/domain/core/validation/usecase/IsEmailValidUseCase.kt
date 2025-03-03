package com.example.tbcacademyhomework.domain.core.validation.usecase

import com.example.tbcacademyhomework.domain.core.validation.UserDataValidator
import javax.inject.Inject

class IsEmailValidUseCase @Inject constructor(
    private val userDataValidator: UserDataValidator
) {
    operator fun invoke(email: String) = userDataValidator.isEmailValid(email)
}