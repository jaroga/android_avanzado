package com.keepcoding.imgram.model.network

import com.squareup.moshi.Json

data class TvShowItemNetworkData(
    @Json(name = "id") var id: Long?,
    @Json(name = "name") var name: String?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "vote_average") var voteAverage: Double?,
    @Json(name = "overview") var overview: String?,
)