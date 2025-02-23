package com.example.tbcacademyhomework.presentation.home

import com.example.tbcacademyhomework.presentation.model.Post
import com.example.tbcacademyhomework.presentation.model.Story

data class HomeScreenState(
    val stories: List<Story> = emptyList(),
    val posts: List<Post> = emptyList(),
    val isStoriesLoading: Boolean = true,
    val isPostsLoading: Boolean = true
)
