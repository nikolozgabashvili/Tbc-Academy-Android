package com.example.tbcacademyhomework.data.remote.repository

import com.example.tbcacademyhomework.data.remote.api_service.PostApiService
import com.example.tbcacademyhomework.data.remote.model.mappers.toDomain
import com.example.tbcacademyhomework.data.remote.util.HttpRequestHelper
import com.example.tbcacademyhomework.domain.model.post.PostDomain
import com.example.tbcacademyhomework.domain.repository.PostRepository
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postApiService: PostApiService,
    private val httpRequestHelper: HttpRequestHelper

) : PostRepository {


    override suspend fun getPosts(): Flow<Resource<List<PostDomain>, DataError>> {
        return httpRequestHelper.safeCall {
            postApiService.fetchPosts()
        }.map { resource ->
            resource.map { it.toDomain() }
        }
    }
}