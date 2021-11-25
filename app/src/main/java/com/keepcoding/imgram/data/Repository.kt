package com.keepcoding.imgram.data

import com.keepcoding.imgram.model.TvShowItemData
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) {
//class Repository() {
//
//    private val remoteDataSource = RemoteDataSource()
//    private val localDataSource = LocalDataSource()

    suspend fun getTvShows(): List<TvShowItemData>{
        return remoteDataSource.getTopShows()
    }
}