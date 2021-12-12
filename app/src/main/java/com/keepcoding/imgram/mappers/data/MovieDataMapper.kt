package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.localdata.MovieItemLocalData
import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.network.MovieItemNetworkData
import javax.inject.Inject

class MovieDataMapper @Inject constructor() {

    private fun mapLocalToData(local: MovieItemLocalData): MovieItemData {
        return MovieItemData(local.id, local.title, local.posterPath, local.popularity, local.releaseDate)
    }

    fun mapLocalToData(localList: List<MovieItemLocalData>): List<MovieItemData> {
        return localList.map { mapLocalToData(it) }
    }

    private fun mapNetworkToData(network: MovieItemNetworkData): MovieItemData {
        return MovieItemData(network.id, network.title, network.posterPath, network.popularity, network.releaseDate)
    }

    fun mapNetworkToData(networkList: List<MovieItemNetworkData>): List<MovieItemData> {
        return networkList.map { mapNetworkToData(it) }
    }

    private fun mapDataToLocal(local: MovieItemData): MovieItemLocalData {
        return MovieItemLocalData(local.id, local.title, local.posterPath, local.popularity, local.releaseDate)
    }

    fun mapDataToLocal(localList: List<MovieItemData>): List<MovieItemLocalData> {
        return localList.map { mapDataToLocal(it) }
    }
}