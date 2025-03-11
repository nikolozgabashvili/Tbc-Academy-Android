package com.example.tbcacademyhomework.data.core.connectivity_observer

import com.example.tbcacademyhomework.domain.connectivity_observer.ConnectivityObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConObserverModule {

    @Binds
    @Singleton
    abstract fun bindsConnectivityObserver(observerImpl: NetworkConnectivityObserver): ConnectivityObserver
}