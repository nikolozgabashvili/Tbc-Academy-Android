package com.example.tbcacademyhomework.domain.repository

import com.example.tbcacademyhomework.domain.model.story.StoryDomain
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    suspend fun getStories(): Flow<Resource<List<StoryDomain>, DataError>>
}