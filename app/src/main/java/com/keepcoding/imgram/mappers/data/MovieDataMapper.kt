package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.network.MovieItemNetworkData
import javax.inject.Inject

class MovieDataMapper @Inject constructor() {

    private fun mapNetworkToData(network: MovieItemNetworkData): MovieItemData {
        return MovieItemData(network.id, network.title, network.posterPath, network.popularity, network.releaseDate)
    }

    fun mapNetworkToData(networkList: List<MovieItemNetworkData>): List<MovieItemData> {
        return networkList.map { mapNetworkToData(it) }
    }
}