package com.keepcoding.imgram.mappers.presentation

import com.keepcoding.imgram.model.data.TvShowItemData
import com.keepcoding.imgram.model.presentation.TvShowPresentation
import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.presentation.MoviePresentation
import javax.inject.Inject

class MoviePresentationMapper @Inject constructor() {

    fun mapDataToPresentation(data: List<MovieItemData>): List<MoviePresentation> {
        return data.map { mapDataToPresentation(it) }
    }

    fun mapDataToPresentation(data: MovieItemData): MoviePresentation {
        return MoviePresentation(data.id, data.title, data.posterPath, data.popularity, data.releaseDate)
    }

}