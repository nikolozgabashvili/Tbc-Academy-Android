package com.example.tbcacademyhomework.data.core.managers.theme.di

import com.example.tbcacademyhomework.data.core.managers.theme.repository.ThemeRepositoryImpl
import com.example.tbcacademyhomework.domain.managers.theme.repository.ThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ThemeModule {

    @Binds
    @Singleton
    abstract fun bindThemeRepository(
        themeRepositoryImpl: ThemeRepositoryImpl
    ): ThemeRepository


}