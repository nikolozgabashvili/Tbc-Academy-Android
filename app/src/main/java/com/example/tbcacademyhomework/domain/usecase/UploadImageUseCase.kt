package com.example.tbcacademyhomework.domain.usecase

import com.example.tbcacademyhomework.domain.repsoitory.ImageStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val repository: ImageStorageRepository
) {
    suspend operator fun invoke(byteArray: ByteArray) = withContext(Dispatchers.IO) {
        repository.uploadImage(byteArray)
    }
}