package com.keepcoding.imgram.data

import android.util.Log
import com.keepcoding.imgram.data.local.LocalDataSource
import com.keepcoding.imgram.data.remote.RemoteDataSource
import com.keepcoding.imgram.mappers.TvShowDataMapper
import com.keepcoding.imgram.model.TvShowItemData
import com.keepcoding.imgram.model.TvShowItemLocalData
import com.keepcoding.imgram.model.TvShowItemNetworkData
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                     private val localDataSource: LocalDataSource,
                                     private val dataMapper: TvShowDataMapper) {
//class Repository() {
//
//    private val remoteDataSource = RemoteDataSource()
//    private val localDataSource = LocalDataSource()

    private suspend fun getRemoteTvShows(): List<TvShowItemNetworkData>{
        val remoteResult = remoteDataSource.getTopShows()
        Log.d("Repository", "Resultados remotos: ${remoteResult.size}" )
        return remoteResult
    }

    private suspend fun getLocalTvShows(): List<TvShowItemLocalData> {
        val localResult = localDataSource.getTvShows()
        Log.d("Repository", "Resultados locales: ${localResult.size}" )
        return localResult
    }

    suspend fun getTvShows(): List<TvShowItemData>{
        val result = arrayListOf<TvShowItemData>()
        result.addAll(dataMapper.mapLocalToData(getLocalTvShows()))

        if (result.isEmpty()){
            val remote = dataMapper.mapNetworkToData(getRemoteTvShows())
            localDataSource.insertTvShows(dataMapper.mapDataToLocal(remote))
            result.addAll(dataMapper.mapLocalToData(getLocalTvShows()))
        }

        return result
    }

    suspend fun deleteTvShow(itemData: TvShowItemData){
        localDataSource.deleteTvShow(dataMapper.mapDataToLocal(itemData))
    }

    suspend fun deleteTvShowById(id: Long){
        localDataSource.deleteTvShowById(id)
    }

    suspend fun deleteAllTvShows(){
        localDataSource.deleteAllTvShowS()
    }
}