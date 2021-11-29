package com.keepcoding.imgram.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TvShowItemLocalData(
    @PrimaryKey @ColumnInfo(name = "id") var id: Long?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "poster_path") var posterPath: String?,
)