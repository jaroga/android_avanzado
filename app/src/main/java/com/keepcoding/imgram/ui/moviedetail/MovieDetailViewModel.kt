package com.keepcoding.imgram.ui.moviedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.imgram.data.Repository
import com.keepcoding.imgram.mappers.presentation.MovieDetailPresentationMapper
import com.keepcoding.imgram.model.presentation.MovieDetailPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: Repository,
    private val mapper: MovieDetailPresentationMapper
) : ViewModel() {

    private val _images: MutableLiveData<MovieDetailPresentation> by lazy {
        MutableLiveData<MovieDetailPresentation>()
    }
    val images: LiveData<MovieDetailPresentation> get() = _images

    fun getMovieDetail(movieId: Int){
        viewModelScope.launch {
            val movieDetail = async(Dispatchers.IO){
                val result = repository.getMovieDetail(movieId)
                return@async result
            }
            val moviewReviews = async(Dispatchers.IO) {
                val result = repository.getMovieReviews(movieId)
                return@async result
            }

            _images.postValue(mapper.mapDataToPresentation(movieDetail.await(), moviewReviews.await()))
        }
    }
}