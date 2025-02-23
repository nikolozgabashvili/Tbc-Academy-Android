package com.example.tbcacademyhomework.data.remote.model.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostOwnerDTO(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val profile: String?,
    @SerialName("post_date")
    val postDate: Long

)