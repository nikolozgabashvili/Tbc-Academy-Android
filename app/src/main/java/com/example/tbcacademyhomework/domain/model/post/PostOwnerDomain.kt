package com.example.tbcacademyhomework.domain.model.post


data class PostOwnerDomain(
    val firstName: String,
    val lastName: String,
    val profile: String?,
    val postDate: Long
)