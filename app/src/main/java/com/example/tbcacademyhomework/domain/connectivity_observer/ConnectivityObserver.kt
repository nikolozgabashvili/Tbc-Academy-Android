package com.example.tbcacademyhomework.domain.connectivity_observer

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val isConnected: Flow<Boolean>
}