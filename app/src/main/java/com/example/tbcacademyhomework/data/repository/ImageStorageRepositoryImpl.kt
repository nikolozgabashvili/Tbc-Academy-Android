package com.example.tbcacademyhomework.data.repository

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.tbcacademyhomework.data.worker.UploadImageWorker
import com.example.tbcacademyhomework.domain.repsoitory.ImageStorageRepository
import com.example.tbcacademyhomework.domain.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class ImageStorageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val workManager: WorkManager
) : ImageStorageRepository {
    override suspend fun uploadImage(btArray: ByteArray): Flow<Resource<Unit>> {

        return flow {
            emit(Resource.Loading)
            try {

                val file = File(context.cacheDir, "image_${System.currentTimeMillis()}.jpg")
                file.writeBytes(btArray)

               val uploadRequest =
                    OneTimeWorkRequest.Builder(UploadImageWorker::class.java)
                        .setInputData(workDataOf(UploadImageWorker.TEMP_FILE_PATH to file.absolutePath))
                        .build()
                workManager.beginUniqueWork(
                    UPLOAD_WORKER,
                    ExistingWorkPolicy.KEEP,
                    uploadRequest
                ).enqueue()

                workManager.getWorkInfoByIdFlow(uploadRequest.id).collect {
                    if (it?.state == WorkInfo.State.SUCCEEDED) {
                        emit(Resource.Success(Unit))
                    } else if (it?.state==WorkInfo.State.FAILED) {
                        val errorData = it.outputData.getString(UploadImageWorker.ERROR_KEY)
                        emit(Resource.Error(errorData))
                    }

                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message))
            }


        }
    }

    companion object {
        private const val UPLOAD_WORKER = "upload_worker"
    }

}