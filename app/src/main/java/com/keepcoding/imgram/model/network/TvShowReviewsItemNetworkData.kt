package com.keepcoding.imgram.model.network

import com.squareup.moshi.Json

data class TvShowReviewsItemNetworkData(
    @Json(name = "id") var id: String,
    @Json(name = "author") var author: String?,
    @Json(name = "content") var content: String?,
    @Json(name = "url") var url: String?,
)