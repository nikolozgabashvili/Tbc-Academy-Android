package com.example.tbcacademyhomework.presentation.model

data class Post(
    val id: Long,
    val images: List<String>,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: PostOwner
)