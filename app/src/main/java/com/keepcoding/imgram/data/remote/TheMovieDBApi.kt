package com.keepcoding.imgram.data.remote

import com.keepcoding.imgram.model.PagedResultData
import com.keepcoding.imgram.model.network.*
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBApi {

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTvShows(): PagedResultData<TvShowItemNetworkData>

    @GET("/3/tv/{tvshow_id}")
    suspend fun getTvShowDetail(@Path("tvshow_id") id: Int): TvShowDetailItemNetworkData

    @GET("/3/tv/{tvshow_id}/reviews")
    suspend fun getTvShowReviews(@Path("tvshow_id") id: Int): PagedResultData<TvShowReviewsItemNetworkData>

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): PagedResultData<MovieItemNetworkData>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int): MovieDetailItemNetworkData

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") id: Int): PagedResultData<MovieReviewsItemNetworkData>
}
