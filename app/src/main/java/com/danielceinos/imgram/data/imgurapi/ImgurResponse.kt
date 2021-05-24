package com.danielceinos.imgram.data.imgurapi

data class ImgurResponse<T>(
    val data: T,
    val success: Boolean,
    val status: Int
)