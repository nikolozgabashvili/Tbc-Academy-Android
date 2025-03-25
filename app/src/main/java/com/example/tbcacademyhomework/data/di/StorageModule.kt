package com.example.tbcacademyhomework.data.di

import com.example.tbcacademyhomework.data.repository.ImageStorageRepositoryImpl
import com.example.tbcacademyhomework.domain.repsoitory.ImageStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    @Singleton
    abstract fun bindsStorageRepository(impl: ImageStorageRepositoryImpl): ImageStorageRepository


}