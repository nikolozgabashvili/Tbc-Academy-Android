package com.example.tbcacademyhomework.data.remote.model.mappers

import com.example.tbcacademyhomework.data.remote.model.post.PostDTO
import com.example.tbcacademyhomework.data.remote.model.post.PostOwnerDTO
import com.example.tbcacademyhomework.domain.model.post.PostDomain
import com.example.tbcacademyhomework.domain.model.post.PostOwnerDomain


fun List<PostDTO>.toDomain(): List<PostDomain> {
    return this.map { it.toDomain() }
}

private fun PostDTO.toDomain(): PostDomain {
    return PostDomain(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toDomain()
    )
}

private fun PostOwnerDTO.toDomain(): PostOwnerDomain {
    return PostOwnerDomain(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate
    )
}