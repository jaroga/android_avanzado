package com.keepcoding.imgram.data

import com.keepcoding.imgram.model.TvShowItemData
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

    suspend fun getTopShows(): List<TvShowItemData> {
        val pagedResultData = api.getTopRatedTvShows()
        return pagedResultData.results
    }
}

