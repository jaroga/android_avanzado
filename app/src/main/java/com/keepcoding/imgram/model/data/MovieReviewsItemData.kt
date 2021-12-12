package com.keepcoding.imgram.model.data

import com.squareup.moshi.Json

data class MovieReviewsItemData(
    var id: String,
    var author: String?,
    var content: String?,
    var url: String?,
)