package com.keepcoding.imgram.model.network

import com.squareup.moshi.Json

data class MovieDetailItemNetworkData(
    @Json(name = "id") var id: Long?,
    @Json(name = "title") var title: String?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "popularity") var popularity: Double?,
    @Json(name = "release_date") var releaseDate: String?,
    @Json(name = "overview") var overview: String?
)