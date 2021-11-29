package com.keepcoding.imgram.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.imgram.model.TvShowItemLocalData

@Dao
interface TheMovieDBDAO {

    @Query("SELECT * FROM tv_shows order by name DESC")
//    @Query("SELECT * FROM tv_shows")
    fun getTvShows(): List<TvShowItemLocalData>

//    @Query("SELECT name FROM tv_shows")
//    fun getTvShowNames(): List<String>

    @Insert
    fun insertTvShow(item: TvShowItemLocalData): Long


    @Insert
    fun insertTvShows(items: List<TvShowItemLocalData>)

    @Delete
    fun deleteTvShow(item: TvShowItemLocalData)

    @Query("Delete from tv_shows where :itemId = id")
    fun deleteTvShowById(itemId: Long)

    @Query("Delete from tv_shows")
    fun deleteAllTvShows()
}