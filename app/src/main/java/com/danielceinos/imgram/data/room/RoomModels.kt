package com.danielceinos.imgram.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class RoomImage(
    @PrimaryKey val hash: String,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "likes") val likes: Int,
    @ColumnInfo(name = "datetime") val datetime: Long,
    @ColumnInfo(name = "author") val author: String?
)