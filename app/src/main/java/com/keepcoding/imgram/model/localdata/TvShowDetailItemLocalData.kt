package com.keepcoding.imgram.model.localdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows_detail",
    foreignKeys = [
        ForeignKey(entity = TvShowItemLocalData::class,
            parentColumns = ["id"],
            childColumns = ["tv_show_id"],
            onDelete = ForeignKey.CASCADE
        )])
data class TvShowDetailItemLocalData(
    @PrimaryKey @ColumnInfo(name = "id") var id: Long?,
    @ColumnInfo(name = "tv_show_id") var tv_show_id: Long?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "poster_path") var posterPath: String?,
    @ColumnInfo(name = "overview") var overview: String?,
)