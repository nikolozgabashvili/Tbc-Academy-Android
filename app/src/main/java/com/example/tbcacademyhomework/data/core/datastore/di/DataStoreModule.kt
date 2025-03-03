package com.example.tbcacademyhomework.data.core.datastore.di

import com.example.tbcacademyhomework.data.core.datastore.DataStoreHelperImpl
import com.example.tbcacademyhomework.domain.core.datastore.DataStoreHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun bindsDataStoreHelper(
        dataStoreHelperImpl: DataStoreHelperImpl
    ): DataStoreHelper


}