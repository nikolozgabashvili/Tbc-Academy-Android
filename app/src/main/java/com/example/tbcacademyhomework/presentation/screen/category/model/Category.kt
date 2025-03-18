package com.example.tbcacademyhomework.presentation.screen.category.model

data class Category(
    val id: String,
    val name: String,
    val depthItems: List<DepthIndicator>
)