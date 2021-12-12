package com.keepcoding.imgram.model.presentation

data class TvShowDetailPresentation(
    var id: Long?,
    var name: String?,
    var posterPath: String?,
    var overview: String?,
    var reviews: List<TvShowReviewsPresentation>
)