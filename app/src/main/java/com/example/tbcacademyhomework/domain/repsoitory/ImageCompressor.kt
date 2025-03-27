package com.example.tbcacademyhomework.domain.repsoitory

interface ImageCompressor {
    suspend fun compressImage(uriString: String, percentage: Int): ByteArray?
}