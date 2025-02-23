package com.example.tbcacademyhomework.presentation.util.ext

import com.example.tbcacademyhomework.domain.model.post.PostDomain
import com.example.tbcacademyhomework.domain.model.post.PostOwnerDomain
import com.example.tbcacademyhomework.domain.model.story.StoryDomain
import com.example.tbcacademyhomework.presentation.model.Post
import com.example.tbcacademyhomework.presentation.model.PostOwner
import com.example.tbcacademyhomework.presentation.model.Story
import com.example.tbcacademyhomework.presentation.util.DateMapper

fun StoryDomain.toUi(): Story {
    return Story(
        id = id,
        cover = cover,
        title = title
    )
}

fun PostDomain.toUi(): Post {
    return Post(
        id = id,
        images = images ?: emptyList(),
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toUi()
    )
}

private fun PostOwnerDomain.toUi(): PostOwner {
    return PostOwner(
        fullName = "$firstName $lastName",
        profile = profile,
        postDate = DateMapper.format(postDate)
    )
}