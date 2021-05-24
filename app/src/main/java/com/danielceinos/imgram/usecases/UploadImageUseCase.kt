package com.danielceinos.imgram.usecases

import com.danielceinos.imgram.domain.image.Image
import com.danielceinos.imgram.domain.image.ImageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.joinAll
import timber.log.Timber
import java.io.File

@ExperimentalCoroutinesApi
class UploadImageUseCase(private val imageRepository: ImageRepository) {

    fun execute(fileList: List<File>): Flow<UploadResult> {
        return channelFlow {
            var index = 1
            Timber.d("Uploading ${fileList.size} images")
            offer(UploadResult.Loading)
            fileList.map { file ->
                async {
                    val image = imageRepository.save(file)
                    Timber.d("Offered $index")
                    offer(UploadResult.ImageUploaded(image, index++))
                }
            }.joinAll()

            Timber.d("Finish uploading images")
            offer(UploadResult.Finished(fileList.size + 1))
        }
    }
}

sealed class UploadResult {
    object Loading : UploadResult()
    data class ImageUploaded(val image: Image?, val index: Int) : UploadResult()
    data class Finished(val index: Int) : UploadResult()
}