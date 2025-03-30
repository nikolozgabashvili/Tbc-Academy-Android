package com.example.tbcacademyhomework.domain.usecase

import javax.inject.Inject

class ValidateUserInfoUseCase @Inject constructor() {
    operator fun invoke(userInfo: String): Boolean {

        val isPidValid = userInfo.all { it.isDigit() } && userInfo.length == PID_LENGTH
        val isAccountNumberValid = userInfo.length == ACCOUNT_NUMBER_LENGTH
        val isPhoneNumberValid = userInfo.all { it.isDigit() } && userInfo.length == PHONE_NUMBER_LENGTH

        return isPhoneNumberValid || isPidValid || isAccountNumberValid
    }

    companion object {
        private const val PID_LENGTH = 11
        private const val ACCOUNT_NUMBER_LENGTH = 23
        private const val PHONE_NUMBER_LENGTH = 9

    }
}