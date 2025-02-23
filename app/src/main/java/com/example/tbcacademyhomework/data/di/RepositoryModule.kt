package com.example.tbcacademyhomework.data.di

import com.example.tbcacademyhomework.data.remote.repository.PostRepositoryImpl
import com.example.tbcacademyhomework.data.remote.repository.StoryRepositoryImpl
import com.example.tbcacademyhomework.domain.repository.PostRepository
import com.example.tbcacademyhomework.domain.repository.StoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsPostRepository(impl: PostRepositoryImpl): PostRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsStoryRepository(impl: StoryRepositoryImpl): StoryRepository
}