package com.keepcoding.imgram.ui.tvshowsdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.imgram.data.Repository
import com.keepcoding.imgram.mappers.presentation.TvShowDetailPresentationMapper
import com.keepcoding.imgram.model.presentation.MovieDetailPresentation
import com.keepcoding.imgram.model.presentation.TvShowDetailPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val repository: Repository,
    private val mapper: TvShowDetailPresentationMapper
) : ViewModel() {

    private val _images: MutableLiveData<TvShowDetailPresentation> by lazy {
        MutableLiveData<TvShowDetailPresentation>()
    }
    val images: LiveData<TvShowDetailPresentation> get() = _images

    fun getTvShowDetail(tvShowId: Int){
        viewModelScope.launch {
            val tvShowDetail = async(Dispatchers.IO){
                val result = repository.getTvShowDetail(tvShowId)
                return@async result
            }
            val tvShowReviews = async(Dispatchers.IO) {
                val result = repository.getTvShowReviews(tvShowId)
                return@async result
            }

            _images.postValue(mapper.mapDataToPresentation(tvShowDetail.await(), tvShowReviews.await()))
        }
    }
}