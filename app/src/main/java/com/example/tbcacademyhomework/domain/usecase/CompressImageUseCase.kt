package com.example.tbcacademyhomework.domain.usecase

import com.example.tbcacademyhomework.domain.repsoitory.ImageCompressor
import javax.inject.Inject

class CompressImageUseCase @Inject constructor(private val compressor: ImageCompressor) {
    suspend operator fun invoke(uriString: String,percentage:Int): ByteArray? {
        return compressor.compressImage(uriString,percentage)
    }
}