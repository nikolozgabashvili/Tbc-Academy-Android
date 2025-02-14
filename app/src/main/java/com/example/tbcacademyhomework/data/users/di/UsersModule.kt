package com.example.tbcacademyhomework.data.users.di

import com.example.tbcacademyhomework.data.users.repository.OfflineFirstUsersRepositoryImpl
import com.example.tbcacademyhomework.data.users.repository.UsersLocalRepositoryImpl
import com.example.tbcacademyhomework.data.users.repository.UsersRemoteRepositoryImpl
import com.example.tbcacademyhomework.data.users.service.UsersApiService
import com.example.tbcacademyhomework.domain.users.repository.OfflineFirstUsersRepository
import com.example.tbcacademyhomework.domain.users.repository.UsersLocalRepository
import com.example.tbcacademyhomework.domain.users.repository.UsersRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
abstract class UsersModule {


    @Binds
    abstract fun bindsUserRepository(impl: UsersRemoteRepositoryImpl): UsersRemoteRepository

    @Binds
    abstract fun bindsUserLocalRepository(impl: UsersLocalRepositoryImpl): UsersLocalRepository

    @Binds
    abstract fun bindsOfflineFirstRepository(impl: OfflineFirstUsersRepositoryImpl): OfflineFirstUsersRepository

    companion object {
        @Provides
        fun providesUsersApiService(
            retrofit: Retrofit,
        ): UsersApiService {
            return retrofit.create(UsersApiService::class.java)
        }

    }
}