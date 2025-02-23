package com.example.tbcacademyhomework.domain.model.post


data class PostDomain(
    val id: Long,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: PostOwnerDomain
)
