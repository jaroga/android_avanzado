package com.keepcoding.imgram.data

import com.keepcoding.imgram.model.PagedResultData
import com.keepcoding.imgram.model.TvShowItemData
import retrofit2.http.GET

interface TheMovieDBApi {

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTvShows(): PagedResultData<TvShowItemData>

}
