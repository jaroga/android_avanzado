package com.danielceinos.imgram.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ImageDao {
    @Query("SELECT * FROM images WHERE author = :author")
   suspend fun getAll(author: String): List<RoomImage>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(images: List<RoomImage>)

    @Delete
    suspend fun delete(image: RoomImage)
}