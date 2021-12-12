package com.keepcoding.imgram.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.imgram.model.localdata.*

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


    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getMovies(): List<MovieItemLocalData>

    @Insert
    fun insertMovie(item: MovieItemLocalData): Long

    @Insert
    fun insertMovie(items: List<MovieItemLocalData>)


    @Query("SELECT * FROM movies_detail where movie_id = :movieId")
    fun getMovieDetail(movieId: Int): MovieDetailItemLocalData

    @Insert
    fun insertMovieDetail(item: MovieDetailItemLocalData)


    @Query("SELECT * FROM movies_reviews where movie_id = :movieId")
    fun getMovieReviews(movieId: Int): List<MovieReviewsItemLocalData>

    @Insert
    fun insertMovieReview(item: MovieReviewsItemLocalData): Long

    @Insert
    fun insertMovieReview(item: List<MovieReviewsItemLocalData>)


    @Query("SELECT * FROM tv_shows_detail where tv_show_id = :tvShowId")
    fun getTvShowDetail(tvShowId: Int): TvShowDetailItemLocalData

    @Insert
    fun insertTvShowDetail(item: TvShowDetailItemLocalData)


    @Query("SELECT * FROM tv_shows_reviews where tv_show_id = :tvShowId")
    fun getTvShowReviews(tvShowId: Int): List<TvShowReviewsItemLocalData>

    @Insert
    fun insertTvShowReview(item: TvShowReviewsItemLocalData): Long

    @Insert
    fun insertTvShowReview(item: List<TvShowReviewsItemLocalData>)
}