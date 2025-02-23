package com.example.tbcacademyhomework.domain.repository

import com.example.tbcacademyhomework.domain.model.post.PostDomain
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts(): Flow<Resource<List<PostDomain>, DataError>>
}