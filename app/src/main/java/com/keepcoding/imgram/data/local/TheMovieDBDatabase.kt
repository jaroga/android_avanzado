package com.keepcoding.imgram.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keepcoding.imgram.model.localdata.*

@Database(entities = [TvShowItemLocalData::class, TvShowDetailItemLocalData::class, TvShowReviewsItemLocalData::class,
    MovieItemLocalData::class, MovieDetailItemLocalData::class, MovieReviewsItemLocalData::class], version = 3)
abstract class TheMovieDBDatabase : RoomDatabase() {
    abstract fun tmdbDao(): TheMovieDBDAO
}