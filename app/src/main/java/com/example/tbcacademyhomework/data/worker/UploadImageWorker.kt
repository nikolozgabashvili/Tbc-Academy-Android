package com.example.tbcacademyhomework.data.worker

import android.content.Context
import android.content.pm.ServiceInfo
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.tbcacademyhomework.R
import com.google.firebase.storage.FirebaseStorage
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File

@HiltWorker
class UploadImageWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
) : CoroutineWorker(context, params) {

    private val firebaseStorage = FirebaseStorage.getInstance()

    override suspend fun doWork(): Result {
        setForeground(createForegroundInfo())
        val filePath = inputData.getString(TEMP_FILE_PATH) ?: return Result.failure()
        val file = File(filePath)
        return withContext(Dispatchers.IO) {
            try {
                val path = "images/${System.currentTimeMillis()}.jpg"
                val storageRef = firebaseStorage.reference.child(path)
                storageRef.putFile(Uri.fromFile(file)).await()
                Result.success()
            } catch (e: Exception) {
                Result.failure(Data.Builder().putString(ERROR_KEY, e.message).build())

            }
        }


    }

    private fun createForegroundInfo(): ForegroundInfo {
        val title = applicationContext.getString(R.string.uploading_in_progress)

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle(title)
            .setTicker(title)
            .setSmallIcon(R.drawable.ic_image)
            .setOngoing(true)
            .build()

         return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            ForegroundInfo(1, notification)
        }
    }



    companion object {
        const val ERROR_KEY = "error_key"
        const val TEMP_FILE_PATH = "upload_file_path"
        const val CHANNEL_ID = "UPLOAD_CHANNEL"

    }
}