package com.example.tbcacademyhomework.data.remote.model.mappers

import com.example.tbcacademyhomework.data.remote.model.story.StoryDTO
import com.example.tbcacademyhomework.domain.model.story.StoryDomain

fun StoryDTO.toDomain(): StoryDomain {
    return StoryDomain(
        id = id,
        cover = cover,
        title = title
    )
}

fun List<StoryDTO>.toDomain(): List<StoryDomain> {
    return this.map { it.toDomain() }
}