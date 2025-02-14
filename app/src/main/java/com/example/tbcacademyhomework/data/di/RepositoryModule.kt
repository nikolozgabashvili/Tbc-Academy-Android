package com.example.tbcacademyhomework.data.di

import com.example.tbcacademyhomework.data.remote.repository.PlacesRepositoryImpl
import com.example.tbcacademyhomework.domain.repository.PlacesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsPlacesRepository(impl: PlacesRepositoryImpl): PlacesRepository
}