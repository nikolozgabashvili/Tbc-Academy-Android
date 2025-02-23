package com.example.tbcacademyhomework.data.remote.model.story

import kotlinx.serialization.Serializable

@Serializable
data class StoryDTO(
    val id: Long,
    val cover: String,
    val title: String
)