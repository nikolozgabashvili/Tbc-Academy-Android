package com.example.tbcacademyhomework.data.profile.di

import com.example.tbcacademyhomework.data.profile.profile.repository.ProfileRepositoryImpl
import com.example.tbcacademyhomework.domain.profile.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule{

    @Binds
    abstract fun bindsProfileRepository(impl:ProfileRepositoryImpl): ProfileRepository
}