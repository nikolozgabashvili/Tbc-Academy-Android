package com.example.tbcacademyhomework.data.core.connectivity_observer

import com.example.tbcacademyhomework.domain.connectivity_observer.ConnectivityObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ConObserverModule {

    @Binds
    abstract fun bindsConnectivityObserver(observerImpl: NetworkConnectivityObserver): ConnectivityObserver
}