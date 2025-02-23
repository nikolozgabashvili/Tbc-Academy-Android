package com.example.tbcacademyhomework.data.remote.api_service

import com.example.tbcacademyhomework.data.remote.model.story.StoryDTO
import retrofit2.Response
import retrofit2.http.GET

interface StoryApiService {

    @GET(STORY)
    suspend fun fetchStories(): Response<List<StoryDTO>>


    companion object {
        const val STORY = "00a18030-a8c7-47c4-b0c5-8bff92a29ebf"
    }
}