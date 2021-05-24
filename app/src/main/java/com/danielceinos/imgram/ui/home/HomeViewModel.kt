package com.danielceinos.imgram.ui.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielceinos.imgram.domain.image.Image
import com.danielceinos.imgram.domain.image.ImageRepository
import com.danielceinos.imgram.domain.session.SessionRepository
import com.danielceinos.imgram.ui.home.UploadViewData.Status.*
import com.danielceinos.imgram.usecases.DeleteImageUseCase
import com.danielceinos.imgram.usecases.ShareImageUseCase
import com.danielceinos.imgram.usecases.UploadImageUseCase
import com.danielceinos.imgram.usecases.UploadResult.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File


@ExperimentalCoroutinesApi
class HomeViewModel(
    private val sessionRepository: SessionRepository,
    private val imageRepository: ImageRepository,
    private val uploadImageUseCase: UploadImageUseCase,
    private val shareImageUseCase: ShareImageUseCase,
    private val deleteImageUseCase: DeleteImageUseCase
) : ViewModel() {

    private val homeMutableStateFlow = MutableStateFlow(HomeViewData.empty)
    val homeVD: StateFlow<HomeViewData>
        get() = homeMutableStateFlow

    private val sessionMutableStateFlow = MutableStateFlow(SessionViewData.empty)
    val sessionVD: StateFlow<SessionViewData>
        get() = sessionMutableStateFlow

    private val uploadMutableStateFlow = MutableStateFlow<UploadViewData?>(null)
    val uploadViewData: StateFlow<UploadViewData?>
        get() = uploadMutableStateFlow

    private val loggedUsername: String?
        get() = sessionMutableStateFlow.value.accountName

    private var galleryJob: Job? = null
    private var uploadJob: Job? = null
    private var deleteJob: Job? = null

    fun loadHotGallery() {
        fetchGallery { imageRepository.fetchHot() }
    }

    fun loadTopGallery() {
        fetchGallery { imageRepository.fetchTop() }
    }

    fun loadMyGallery() {
        fetchGallery { imageRepository.fetchByAuthorId(loggedUsername!!) }
    }

    private fun fetchGallery(source: suspend () -> List<Image>) {
        galleryJob?.cancel()
        galleryJob = viewModelScope.launch(Dispatchers.IO) {
            homeMutableStateFlow.value = HomeViewData(emptyList(), true)
            val images = source()
            homeMutableStateFlow.value = HomeViewData.from(images)
        }
    }

    fun uploadFile(imagePathList: List<String>) {
        uploadJob = viewModelScope.launch(Dispatchers.IO) {
            val totalImages = imagePathList.size
            uploadImageUseCase.execute(imagePathList.map { File(it) }).collect { uploadResult ->
                val viewData = when (uploadResult) {
                    Loading -> UploadViewData(UPLOADING, totalImages, 0)
                    is ImageUploaded -> UploadViewData(UPLOADING, totalImages, uploadResult.index)
                    is Finished -> UploadViewData(FINISHED, totalImages, uploadResult.index)
                }
                uploadMutableStateFlow.value = viewData
            }
        }
    }

    fun processIntentData(uri: Uri?) {
        sessionRepository.saveSessionFromOauth2(uri.toString())
        val session = sessionRepository.getSession()
        sessionMutableStateFlow.value = SessionViewData(session != null, session?.accountUsername)
    }

    fun cancelUpload() {
        uploadJob?.cancel()
        uploadMutableStateFlow.value?.let {
            uploadMutableStateFlow.value = UploadViewData(CANCELLED, it.totalImages, it.uploaded)
        }
    }

    fun deleteImage(image: Image) {
        deleteJob = viewModelScope.launch(Dispatchers.IO) {
            homeMutableStateFlow.value = homeMutableStateFlow.value.copy(loading = true)
            val newImage = deleteImageUseCase.execute(image)
            homeMutableStateFlow.value = homeMutableStateFlow.value.remove(newImage).copy(loading = false)
        }
    }

    fun shareImage(image: Image, title: String?, tags: List<Image.Tag>) {
        viewModelScope.launch(Dispatchers.IO) {
            homeMutableStateFlow.value = homeMutableStateFlow.value.copy(loading = true)
            val newImage = shareImageUseCase.execute(image, title, tags)
            homeMutableStateFlow.value = homeMutableStateFlow.value.replace(newImage).copy(loading = false)
        }
    }
}

data class HomeViewData(val images: List<Image>, val loading: Boolean) {
    companion object {
        fun from(images: List<Image>): HomeViewData {
            return HomeViewData(
                images = images.sortedByDescending { it.datetime },
                loading = false
            )
        }

        val empty = HomeViewData(emptyList(), true)
    }

    fun replace(image: Image?): HomeViewData {
        if(image == null) return this
        return copy(images = images.filter { it.id != image.id }.toMutableList().apply {
             add(0,image)
        })
    }
    fun remove(image: Image?): HomeViewData {
        if(image == null) return this
        return copy(images = images.filter { it.id != image.id }.toMutableList())
    }
}

data class SessionViewData(val hasSession: Boolean, val accountName: String?) {
    companion object {
        val empty = SessionViewData(false, null)
    }
}

data class UploadViewData(val status: Status, val totalImages: Int, val uploaded: Int) {
    enum class Status {
        UPLOADING, FINISHED, CANCELLED
    }
}
