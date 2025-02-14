package com.example.tbcacademyhomework.domain.models

data class Place(
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val reactionCount: Int,
    val location:String,
    val rating: Int = 0
)
