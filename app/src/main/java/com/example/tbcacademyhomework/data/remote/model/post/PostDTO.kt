package com.example.tbcacademyhomework.data.remote.model.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDTO(
    val id: Long,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    @SerialName("share_content")
    val shareContent: String,
    val owner: PostOwnerDTO
)