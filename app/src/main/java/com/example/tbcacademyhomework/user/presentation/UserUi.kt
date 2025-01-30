package com.example.tbcacademyhomework.user.presentation

data class UserUi(
    val firstName: String,
    val lastName: String,
    val email: String
) {
    val isValid: Boolean
        get() {
            return firstName.isNotBlank() && lastName.isNotBlank() && isValidEmail(email)
        }
}


