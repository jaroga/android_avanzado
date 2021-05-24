package com.danielceinos.imgram.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielceinos.imgram.data.room.RoomImage
import com.danielceinos.imgram.data.room.ImageDao

@Database(entities = [RoomImage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ImageDao
}