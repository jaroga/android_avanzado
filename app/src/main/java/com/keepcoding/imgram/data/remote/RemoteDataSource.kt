package com.keepcoding.imgram.data.remote

import com.keepcoding.imgram.model.network.MovieItemNetworkData
import com.keepcoding.imgram.model.network.TvShowItemNetworkData
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: TheMovieDBApi) {
//class RemoteDataSource() {
//    private lateinit var api: TheMovieDBApi

//    val client = OkHttpClient().newBuilder().build()
//
//    fun getImage(url: String): String{
//        val request: Request = Request.Builder().url(url).build()
//        val response = client.newCall(request).execute()
//
//        val jsonResponse = response.body.toString()
//
//
//        return jsonResponse
//    }

    suspend fun getTopShows(): List<TvShowItemNetworkData> {
        val pagedResultData = api.getTopRatedTvShows()
        return pagedResultData.results
    }

    suspend fun getPopularMovies(): List<MovieItemNetworkData> {
        val pagedResultData = api.getPopularMovies()
        return pagedResultData.results
    }
}

