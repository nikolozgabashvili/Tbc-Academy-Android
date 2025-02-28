package com.example.tbcacademyhomework.data.remote.di

import com.example.tbcacademyhomework.data.remote.repository.LocationRepositoryImpl
import com.example.tbcacademyhomework.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository
}