package com.example.tbcacademyhomework.data.repository

import com.example.tbcacademyhomework.domain.repsoitory.ImageStorageRepository
import com.example.tbcacademyhomework.domain.util.Resource
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class ImageStorageRepositoryImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage
) : ImageStorageRepository {
    override suspend fun uploadImage(byteArray: ByteArray): Flow<Resource<Unit>> {
        return flow {
            try {
                emit(Resource.Loading)
                val path = "images/${System.currentTimeMillis()}.jpg"
                val storageRef = firebaseStorage.reference.child(path)
                storageRef.putBytes(byteArray).await()
                emit(Resource.Success(Unit))
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }
                emit(Resource.Error(e.message))
            }
        }
    }

}