package com.keepcoding.imgram.model.localdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "movies_reviews",
    foreignKeys = [
        ForeignKey(entity = MovieItemLocalData::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"],
            onDelete = ForeignKey.CASCADE
        )])
data class MovieReviewsItemLocalData(
    @PrimaryKey @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "movie_id") var movie_id: Long?,
    @ColumnInfo(name = "author") var author: String?,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "url") var url: String?,
)