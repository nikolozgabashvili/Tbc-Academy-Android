package com.example.tbcacademyhomework.data.users.di

import com.example.tbcacademyhomework.data.users.repository.UsersLocalRepositoryImpl
import com.example.tbcacademyhomework.data.users.repository.UsersRemoteRepositoryImpl
import com.example.tbcacademyhomework.data.users.service.UsersApiService
import com.example.tbcacademyhomework.domain.users.repository.UsersLocalRepository
import com.example.tbcacademyhomework.domain.users.repository.UsersRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UsersModule {


    @Binds
    @Singleton
    abstract fun bindsUserRepository(impl: UsersRemoteRepositoryImpl): UsersRemoteRepository

    @Binds
    @Singleton
    abstract fun bindsUserLocalRepository(impl: UsersLocalRepositoryImpl): UsersLocalRepository


    companion object {
        @Provides
        @Singleton
        fun providesUsersApiService(
            retrofit: Retrofit,
        ): UsersApiService {
            return retrofit.create(UsersApiService::class.java)
        }

    }
}