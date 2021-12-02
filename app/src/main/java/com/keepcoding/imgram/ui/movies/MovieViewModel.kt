package com.keepcoding.imgram.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.imgram.data.Repository
import com.keepcoding.imgram.mappers.presentation.MoviePresentationMapper
import com.keepcoding.imgram.model.presentation.MoviePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: Repository,
    private val mapper: MoviePresentationMapper
) : ViewModel() {

    private val _images: MutableLiveData<List<MoviePresentation>> by lazy {
        MutableLiveData<List<MoviePresentation>>()
    }
    val images: LiveData<List<MoviePresentation>> get() = _images

    init {
        getMovies()
    }

    private fun getMovies(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getPopularMovies()
            }

            _images.postValue(mapper.mapDataToPresentation(result))
        }
    }
}