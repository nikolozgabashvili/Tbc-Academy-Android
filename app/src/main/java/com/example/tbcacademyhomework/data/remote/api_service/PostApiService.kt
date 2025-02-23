package com.example.tbcacademyhomework.data.remote.api_service

import com.example.tbcacademyhomework.data.remote.model.post.PostDTO
import retrofit2.Response
import retrofit2.http.GET

interface PostApiService {

    @GET(POSTS)
    suspend fun fetchPosts(): Response<List<PostDTO>>


    companion object {
        const val POSTS = "1ba8b612-8391-41e5-8560-98e4a48decc7"
    }

}