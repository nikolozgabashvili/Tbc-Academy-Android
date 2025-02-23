package com.example.tbcacademyhomework.domain.usecases

import com.example.tbcacademyhomework.domain.model.post.PostDomain
import com.example.tbcacademyhomework.domain.repository.PostRepository
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<PostDomain>, DataError>> {
        return postRepository.getPosts()
    }
}