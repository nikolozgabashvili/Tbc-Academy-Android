package com.example.tbcacademyhomework.domain.usecases

import com.example.tbcacademyhomework.domain.model.story.StoryDomain
import com.example.tbcacademyhomework.domain.repository.StoryRepository
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetStoriesUseCase @Inject constructor(
    private val storyRepository: StoryRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<StoryDomain>, DataError>> {
        return storyRepository.getStories()
    }
}