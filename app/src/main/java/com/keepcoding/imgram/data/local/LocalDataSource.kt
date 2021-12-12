package com.keepcoding.imgram.data.local

import com.keepcoding.imgram.model.localdata.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: TheMovieDBDAO) {

    fun getTvShows(): List<TvShowItemLocalData> {
        return dao.getTvShows()
    }

    fun insertTvShow(itemLocalData: TvShowItemLocalData){
        dao.insertTvShow(itemLocalData)
    }

    fun insertTvShows(itemsLocalData: List<TvShowItemLocalData>){
        dao.insertTvShows(itemsLocalData)
    }

    fun deleteTvShow(itemLocalData: TvShowItemLocalData){
        dao.deleteTvShow(itemLocalData)
    }

    fun deleteTvShowById(id: Long){
        dao.deleteTvShowById(id)
    }

    fun deleteAllTvShowS(){
        dao.deleteAllTvShows()
    }

    fun getTvShowDetail(id: Int): TvShowDetailItemLocalData {
        return dao.getTvShowDetail(id)
    }

    fun insertTvShowDetail(itemsLocalData: TvShowDetailItemLocalData) {
        return dao.insertTvShowDetail(itemsLocalData)
    }

    fun getTvShowReviews(id: Int): List<TvShowReviewsItemLocalData> {
        return dao.getTvShowReviews(id)
    }

    fun insertTvShowReviews(itemsLocalData: List<TvShowReviewsItemLocalData>) {
        return dao.insertTvShowReview(itemsLocalData)
    }

    fun getMovies(): List<MovieItemLocalData> {
        return dao.getMovies()
    }

    fun insertMovies(itemsLocalData: List<MovieItemLocalData>){
        dao.insertMovie(itemsLocalData)
    }

    fun getMovieDetail(id: Int): MovieDetailItemLocalData {
        return dao.getMovieDetail(id)
    }

    fun insertMovieDetail(itemsLocalData: MovieDetailItemLocalData) {
        return dao.insertMovieDetail(itemsLocalData)
    }

    fun getMovieReviews(id: Int): List<MovieReviewsItemLocalData> {
        return dao.getMovieReviews(id)
    }

    fun insertMovieReviews(itemsLocalData: List<MovieReviewsItemLocalData>) {
        return dao.insertMovieReview(itemsLocalData)
    }
}