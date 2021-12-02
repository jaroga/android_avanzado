package com.keepcoding.imgram.model.data

import com.squareup.moshi.Json

data class MovieItemData(
    var id: Long?,
    var title: String?,
    var posterPath: String?,
    var popularity: Double?,
    var releaseDate: String?,
)