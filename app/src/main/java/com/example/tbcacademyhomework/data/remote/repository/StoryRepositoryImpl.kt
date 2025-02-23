package com.example.tbcacademyhomework.data.remote.repository

import com.example.tbcacademyhomework.data.remote.api_service.StoryApiService
import com.example.tbcacademyhomework.data.remote.model.mappers.toDomain
import com.example.tbcacademyhomework.data.remote.util.HttpRequestHelper
import com.example.tbcacademyhomework.domain.model.story.StoryDomain
import com.example.tbcacademyhomework.domain.repository.StoryRepository
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val storyApiService: StoryApiService,
    private val httpRequestHelper: HttpRequestHelper
) : StoryRepository {


    override suspend fun getStories(): Flow<Resource<List<StoryDomain>, DataError>> {
        return httpRequestHelper.safeCall {
            storyApiService.fetchStories()
        }.map { resource ->
            resource.map { storyList ->
                storyList.toDomain()
            }
        }
    }

}