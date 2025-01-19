package com.example.tbcacademyhomework.network.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkError(val code: Int?, val error: String)
