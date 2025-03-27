package com.example.tbcacademyhomework.data.image_compressor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.net.toUri
import com.example.tbcacademyhomework.domain.repsoitory.ImageCompressor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class Compressor @Inject constructor(
    @ApplicationContext private val context: Context
) : ImageCompressor {
    override suspend fun compressImage(uriString: String,percentage:Int): ByteArray? {
        require(percentage in 1..100)
        val uri = uriString.toUri()
        return withContext(Dispatchers.IO) {
            val inputBytes = context
                .contentResolver
                .openInputStream(uri)?.use { inputStream ->
                    inputStream.readBytes()
                } ?: return@withContext null

            withContext(Dispatchers.Default) {
                val bitmap = BitmapFactory.decodeByteArray(inputBytes, 0, inputBytes.size)

                var outputBytes: ByteArray
                ByteArrayOutputStream().use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, percentage, outputStream)
                    outputBytes = outputStream.toByteArray()
                }

                outputBytes

            }
        }
    }
}