package com.danielceinos.imgram.domain.image

data class Image(
    val id: String,
    val title: String?,
    val url: String,
    val likes: Int,
    val datetime: Long,
    val author: String?,
    val published: Boolean,
    val tags: List<Tag>
) {

    val authorAvatar: String
        get() = "https://imgur.com/user/${author}/avatar"

    fun canDelete(author: String?) = this.author == author

    data class Tag(
        val name: String
    )

    fun shareImage(title: String?, tags: List<Tag>): Image {
        return copy(title = title, tags = tags, published = true)
    }
}