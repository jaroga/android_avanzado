package com.keepcoding.imgram.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keepcoding.imgram.model.TvShowItemLocalData

@Database(entities = [TvShowItemLocalData::class], version = 1)
abstract class TheMovieDBDatabase : RoomDatabase() {
    abstract fun tmdbDao(): TheMovieDBDAO
}