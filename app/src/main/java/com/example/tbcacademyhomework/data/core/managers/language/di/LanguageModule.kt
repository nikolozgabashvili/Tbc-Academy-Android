package com.example.tbcacademyhomework.data.core.managers.language.di

import com.example.tbcacademyhomework.data.core.managers.language.repository.LanguageRepositoryImpl
import com.example.tbcacademyhomework.domain.managers.language.repository.LanguageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class LanguageModule {

    @Binds
    @Singleton
    abstract fun bindsLanguageRepository(
        languageRepositoryImpl: LanguageRepositoryImpl
    ): LanguageRepository

}