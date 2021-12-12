package com.keepcoding.imgram.model.localdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "movies_detail",
    foreignKeys = [
        ForeignKey(entity = MovieItemLocalData::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"],
            onDelete = ForeignKey.CASCADE
        )])
data class MovieDetailItemLocalData(
    @PrimaryKey @ColumnInfo(name = "id") var id: Long?,
    @ColumnInfo(name = "movie_id") var movie_id: Long?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "poster_path") var posterPath: String?,
    @ColumnInfo(name = "popularity") var popularity: Double?,
    @ColumnInfo(name = "releaseDate") var releaseDate: String?,
    @ColumnInfo(name = "overview") var overview: String?,
)