package com.keepcoding.imgram.mappers.presentation

import com.keepcoding.imgram.model.data.MovieDetailItemData
import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.data.MovieReviewsItemData
import com.keepcoding.imgram.model.presentation.MovieDetailPresentation
import com.keepcoding.imgram.model.presentation.MoviePresentation
import com.keepcoding.imgram.model.presentation.MovieReviewsPresentation
import javax.inject.Inject

class MovieDetailPresentationMapper @Inject constructor() {
    fun mapDataToPresentation(data: List<MovieReviewsItemData>): List<MovieReviewsPresentation> {
        return data.map { MovieReviewsPresentation(it.id, it.author, it.content, it.url) }
    }

    fun mapDataToPresentation(movieDetail: MovieDetailItemData, moviewReviews: List<MovieReviewsItemData> ): MovieDetailPresentation {
        return MovieDetailPresentation(movieDetail.id, movieDetail.title, movieDetail.posterPath,
            movieDetail.popularity, movieDetail.releaseDate, movieDetail.overview, mapDataToPresentation(moviewReviews))
    }

}