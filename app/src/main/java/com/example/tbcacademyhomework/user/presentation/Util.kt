package com.example.tbcacademyhomework.user.presentation

import android.util.Patterns
import com.example.bcacademyhomework.storage.User

fun UserUi.toUser(): User {
    return User.newBuilder()
        .setEmail(email)
        .setLastName(lastName)
        .setFirstName(firstName)
        .build()
}

fun User.toUserUi(): UserUi {
    return UserUi(
        firstName = firstName,
        lastName = lastName,
        email = email
    )
}

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}