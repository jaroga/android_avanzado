package com.danielceinos.imgram.data.imgurapi

data class ImgurImage(
    val account_id: String?,
    val account_url: String?,
    val datetime: Long,
    val deletehash: String?,
    val description: String?,
    val favorite: Boolean,
    val id: String,
    val in_gallery: Boolean,
    val in_most_viral: Boolean,
    val link: String,
    val name: String?,
    val tags: List<ImgurTag>,
    val title: String?,
    val type: String,
    val views: Int
)