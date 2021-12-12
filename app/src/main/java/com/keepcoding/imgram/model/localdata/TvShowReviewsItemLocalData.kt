package com.keepcoding.imgram.model.localdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows_reviews",
    foreignKeys = [
        ForeignKey(entity = TvShowItemLocalData::class,
            parentColumns = ["id"],
            childColumns = ["tv_show_id"],
            onDelete = ForeignKey.CASCADE
        )])
data class TvShowReviewsItemLocalData(
    @PrimaryKey @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "tv_show_id") var tv_show_id: Long?,
    @ColumnInfo(name = "author") var author: String?,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "url") var url: String?,
)