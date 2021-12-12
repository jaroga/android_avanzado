package com.keepcoding.imgram.data

import android.util.Log
import com.keepcoding.imgram.data.local.LocalDataSource
import com.keepcoding.imgram.data.remote.RemoteDataSource
import com.keepcoding.imgram.mappers.data.*
import com.keepcoding.imgram.model.data.*
import com.keepcoding.imgram.model.localdata.*
import com.keepcoding.imgram.model.network.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val tvShowDataMapper: TvShowDataMapper,
    private val tvShowDetailDataMapper: TvShowDetailDataMapper,
    private val tvShowReviewsDataMapper: TvShowReviewsDataMapper,
    private val movieDataMapper: MovieDataMapper,
    private val movieDetailDataMapper: MovieDetailDataMapper,
    private val movieReviewsDataMapper: MovieReviewsDataMapper,
) {
//class Repository() {
//
//    private val remoteDataSource = RemoteDataSource()
//    private val localDataSource = LocalDataSource()

    private suspend fun getRemoteTvShows(): List<TvShowItemNetworkData> {
        val remoteResult = remoteDataSource.getTopShows()
        Log.d("Repository", "Tv Shows Resultados remotos: ${remoteResult.size}")
        return remoteResult
    }

    private suspend fun getRemoteTvShowDetail(id: Int): TvShowDetailItemNetworkData {
        val remoteResult = remoteDataSource.getTvShowDetail(id)
        Log.d("Repository", "Tv Shows Detail Resultados remotos: ${remoteResult}")
        return remoteResult
    }

    private suspend fun getRemoteTvShowReviews(id: Int): List<TvShowReviewsItemNetworkData> {
        val remoteResult = remoteDataSource.getTvShowReviews(id)
        Log.d("Repository", "Tv Shows Reviews Resultados remotos: ${remoteResult.size}")
        return remoteResult
    }

    private suspend fun getRemotePopularMovies(): List<MovieItemNetworkData> {
        val remoteResult = remoteDataSource.getPopularMovies()
        Log.d("Repository", "Movies Resultados remotos: ${remoteResult.size}")
        return remoteResult
    }

    private suspend fun getRemoteMovieDetail(id: Int): MovieDetailItemNetworkData {
        val remoteResult = remoteDataSource.getMovieDetail(id)
        Log.d("Repository", "Movie Detail Resultados remotos: ${remoteResult}")
        return remoteResult
    }

    private suspend fun getRemoteMovieReviews(id: Int): List<MovieReviewsItemNetworkData> {
        val remoteResult = remoteDataSource.getMovieReviews(id)
        Log.d("Repository", "Movie Reviews Resultados remotos: ${remoteResult.size}")
        return remoteResult
    }

    private suspend fun getLocalTvShows(): List<TvShowItemLocalData> {
        val localResult = localDataSource.getTvShows()
        Log.d("Repository", "Tv Shows Resultados locales: ${localResult.size}")
        return localResult
    }

    private suspend fun getLocalTvShowsDetail(id: Int): TvShowDetailItemLocalData {
        val localResult = localDataSource.getTvShowDetail(id)
        Log.d("Repository", "Tv Shows Resultados locales ${localResult}")
        return localResult
    }

    private suspend fun getLocalTvShowsReviews(id: Int): List<TvShowReviewsItemLocalData> {
        val localResult = localDataSource.getTvShowReviews(id)
        Log.d("Repository", "Tv Shows Reviews Resultados locales: ${localResult.size}")
        return localResult
    }

    suspend fun getTvShows(): List<TvShowItemData> {
        val result = arrayListOf<TvShowItemData>()
        result.addAll(tvShowDataMapper.mapLocalToData(getLocalTvShows()))

        if (result.isEmpty()) {
            val remote = tvShowDataMapper.mapNetworkToData(getRemoteTvShows())
            localDataSource.insertTvShows(tvShowDataMapper.mapDataToLocal(remote))
            result.addAll(tvShowDataMapper.mapLocalToData(getLocalTvShows()))
        }

        return result
    }

    suspend fun getTvShowDetail(id: Int): TvShowDetailItemData {
        var result: TvShowDetailItemData? = null
        val localResult = getLocalTvShowsDetail(id)

        if (localResult != null) {
            return tvShowDetailDataMapper.mapLocalToData(localResult)

        } else {
            val remote = tvShowDetailDataMapper.mapNetworkToData(getRemoteTvShowDetail(id))
            localDataSource.insertTvShowDetail(tvShowDetailDataMapper.mapDataToLocal(remote))
            return remote
        }
    }

    suspend fun getTvShowReviews(id: Int): List<TvShowReviewsItemData> {
        val result = arrayListOf<TvShowReviewsItemData>()
        result.addAll(tvShowReviewsDataMapper.mapLocalToData(getLocalTvShowsReviews(id)))

        if (result.isEmpty()) {
            val remote = tvShowReviewsDataMapper.mapNetworkToData(getRemoteTvShowReviews(id))
            localDataSource.insertTvShowReviews(tvShowReviewsDataMapper.mapDataToLocal(id, remote))
            result.addAll(remote)
        }

        return result
    }

    private suspend fun getLocalMovies(): List<MovieItemLocalData> {
        val localResult = localDataSource.getMovies()
        Log.d("Repository", "Movies Resultados locales: ${localResult.size}")
        return localResult
    }

    private suspend fun getLocalMovieDetail(id: Int): MovieDetailItemLocalData {
        val localResult = localDataSource.getMovieDetail(id)
        Log.d("Repository", "Movie Detail Resultados locales: ${localResult}")
        return localResult
    }

    private suspend fun getLocalMovieReviews(id: Int): List<MovieReviewsItemLocalData> {
        val localResult = localDataSource.getMovieReviews(id)
        Log.d("Repository", "Movie Reviews Resultados locales: ${localResult.size}")
        return localResult
    }

    suspend fun getPopularMovies(): List<MovieItemData> {
        val result = arrayListOf<MovieItemData>()
        result.addAll(movieDataMapper.mapLocalToData(getLocalMovies()))

        if (result.isEmpty()) {
            val remote = movieDataMapper.mapNetworkToData(getRemotePopularMovies())
            localDataSource.insertMovies(movieDataMapper.mapDataToLocal(remote))
            result.addAll(remote)
        }

        return result
    }

    suspend fun getMovieDetail(id: Int): MovieDetailItemData {
        var result: MovieDetailItemData? = null
        val localResult = getLocalMovieDetail(id)

        if (localResult != null) {
            return movieDetailDataMapper.mapLocalToData(localResult)

        } else {
            val remote = movieDetailDataMapper.mapNetworkToData(getRemoteMovieDetail(id))
            localDataSource.insertMovieDetail(movieDetailDataMapper.mapDataToLocal(remote))
            return remote
        }
    }

    suspend fun getMovieReviews(id: Int): List<MovieReviewsItemData> {
        val result = arrayListOf<MovieReviewsItemData>()
        result.addAll(movieReviewsDataMapper.mapLocalToData(getLocalMovieReviews(id)))

        if (result.isEmpty()) {
            val remote = movieReviewsDataMapper.mapNetworkToData(getRemoteMovieReviews(id))
            localDataSource.insertMovieReviews(movieReviewsDataMapper.mapDataToLocal(id, remote))
            result.addAll(remote)
        }

        return result
    }

    suspend fun deleteTvShow(itemData: TvShowItemData) {
        localDataSource.deleteTvShow(tvShowDataMapper.mapDataToLocal(itemData))
    }

    suspend fun deleteTvShowById(id: Long) {
        localDataSource.deleteTvShowById(id)
    }

    suspend fun deleteAllTvShows() {
        localDataSource.deleteAllTvShowS()
    }
}