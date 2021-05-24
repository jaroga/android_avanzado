package com.danielceinos.imgram.domain.image

import com.danielceinos.imgram.data.imgurapi.ImgurApi
import com.danielceinos.imgram.data.imgurapi.ImgurGallery
import com.danielceinos.imgram.data.imgurapi.ImgurImage
import com.danielceinos.imgram.data.imgurapi.ImgurResponse
import com.danielceinos.imgram.data.room.ImageDao
import com.danielceinos.imgram.data.room.RoomImage
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File


class ImageRepository(
    private val imgurApi: ImgurApi,
    private val imgurLocal: ImageDao
) {

    suspend fun fetchHot(): List<Image> {
        val gallery: ImgurResponse<List<ImgurGallery>>? = try {
            imgurApi.getHotGallery()
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
        return if (gallery?.success == true) gallery.data.mapNotNull { fromNetworkToDomain(it) } else emptyList()
    }

    suspend fun fetchTop(): List<Image> {
        val gallery = try {
            imgurApi.getTopGallery()
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
        return if (gallery?.success == true) gallery.data.mapNotNull { fromNetworkToDomain(it) } else emptyList()
    }

    suspend fun fetchByAuthorId(authorId: String): List<Image> {
        return try {
            val galleryList = imgurApi.getUsernameGalleries(authorId).let { gallery ->
                gallery.data.mapNotNull { fromNetworkToDomain(it) }
            }
            val imageList = imgurApi.getUsernameImages(authorId).let { images ->
                images.data.mapNotNull { fromNetworkToDomain(it) }
                    .map { image ->
                        galleryList.find { it.id == image.id }?.let { image.copy(published = true, tags = it.tags) } ?: image
                    }
                    .also { imgurLocal.insertAll(it.map { fromDomainToRoom(it) }) }
            }
            imageList
        } catch (e: Exception) {
            Timber.e(e)
            imgurLocal.getAll(authorId).map { fromRoomToDomain(it) }
        }
    }

    suspend fun save(file: File): Image? {
        val requestFile: RequestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, requestFile)
        val response = try {
            imgurApi.uploadImage(body)
        } catch (e: Exception) {
            null
        }
        return fromNetworkToDomain(response?.data)
    }

    suspend fun delete(image: Image) {
        imgurApi.deleteImage(image.id)
    }

    private fun fromNetworkToDomain(imgurGallery: ImgurGallery): Image? {
        return if (imgurGallery.is_album) {
            fromNetworkToDomain(imgurGallery.images!!.first())
                ?.copy(
                    title = imgurGallery.title,
                    author = imgurGallery.account_url,
                    tags = imgurGallery.tags.map { Image.Tag(name = it.display_name) },
                    likes = imgurGallery.favorite_count,
                    published = true
                )
        } else {
            Image(
                title = imgurGallery.title,
                url = imgurGallery.link,
                likes = imgurGallery.favorite_count,
                author = imgurGallery.account_url,
                datetime = imgurGallery.datetime,
                id = imgurGallery.id,
                published = true,
                tags = imgurGallery.tags.map { Image.Tag(name = it.display_name) }
            )
        }
    }

    private fun fromNetworkToDomain(imgurImage: ImgurImage?): Image? {
        if (imgurImage == null) return null

        return Image(
            title = imgurImage.title,
            url = imgurImage.link,
            likes = 0,
            author = imgurImage.account_url,
            datetime = imgurImage.datetime,
            id = imgurImage.id,
            published = false,
            tags = emptyList()
        )
    }

    private fun fromDomainToRoom(image: Image): RoomImage {
        return RoomImage(
            hash = image.id,
            title = image.title,
            author = image.author,
            datetime = image.datetime,
            likes = image.likes,
            url = image.url
        )
    }

    private fun fromRoomToDomain(roomImage: RoomImage): Image {
        return Image(
            title = roomImage.title,
            id = roomImage.hash,
            author = roomImage.author,
            datetime = roomImage.datetime,
            likes = roomImage.likes,
            url = roomImage.url,
            published = false,
            tags = emptyList()
        )
    }

    suspend fun shareImage(imageId: String, title: String, tags: List<Image.Tag>) {
        imgurApi.shareImage(imageId, title, tags.joinToString { it.name })
    }
}