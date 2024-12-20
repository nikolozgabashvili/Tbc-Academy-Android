package com.example.tbcacademyhomework

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val address: String,
    val email: String,
    val desc: String? = null
)
