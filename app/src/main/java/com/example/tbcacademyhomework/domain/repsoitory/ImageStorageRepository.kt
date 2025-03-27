package com.example.tbcacademyhomework.domain.repsoitory

import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ImageStorageRepository {
    suspend fun uploadImage(btArray: ByteArray): Flow<Resource<Unit>>
}