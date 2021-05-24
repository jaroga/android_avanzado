package com.danielceinos.imgram.usecases

import com.danielceinos.imgram.domain.image.Image
import com.danielceinos.imgram.domain.image.ImageRepository
import timber.log.Timber

class ShareImageUseCase(private val imageRepository: ImageRepository) {

    suspend fun execute(image: Image, title: String?, tags: List<Image.Tag>): Image {
        return try {
            imageRepository.shareImage(image.id, title ?: "No title", tags)
            image.shareImage(title, tags)
        } catch (e: Exception) {
            Timber.e(e)
            image
        }
    }
}