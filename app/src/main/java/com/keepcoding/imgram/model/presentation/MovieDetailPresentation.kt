package com.keepcoding.imgram.model.presentation

data class MovieDetailPresentation(
    var id: Long?,
    var title: String?,
    var posterPath: String?,
    var popularity: Double?,
    var releaseDate: String?,
    var overview: String?,
    var reviews: List<MovieReviewsPresentation>,
)