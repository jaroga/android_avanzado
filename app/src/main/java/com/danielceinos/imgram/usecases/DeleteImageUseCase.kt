package com.danielceinos.imgram.usecases

import com.danielceinos.imgram.domain.image.Image
import com.danielceinos.imgram.domain.image.ImageRepository
import timber.log.Timber

class DeleteImageUseCase(private val imageRepository: ImageRepository) {

    suspend fun execute(image: Image): Image? {
        return try {
            imageRepository.delete(image)
            image
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }
}