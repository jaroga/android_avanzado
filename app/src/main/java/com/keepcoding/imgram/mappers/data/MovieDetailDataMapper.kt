package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.MovieDetailItemData
import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.localdata.MovieDetailItemLocalData
import com.keepcoding.imgram.model.localdata.MovieItemLocalData
import com.keepcoding.imgram.model.network.MovieDetailItemNetworkData
import javax.inject.Inject

class MovieDetailDataMapper @Inject constructor() {
    fun mapNetworkToData(network: MovieDetailItemNetworkData): MovieDetailItemData {
        return MovieDetailItemData(network.id, network.title, network.posterPath, network.popularity, network.releaseDate, network.overview)
    }

    fun mapLocalToData(local: MovieDetailItemLocalData): MovieDetailItemData {
        return MovieDetailItemData(local.id, local.title, local.posterPath, local.popularity, local.releaseDate, local.overview)
    }

    fun mapDataToLocal(local: MovieDetailItemData): MovieDetailItemLocalData {
        return MovieDetailItemLocalData(local.id, local.id, local.title, local.posterPath, local.popularity, local.releaseDate, local.overview)
    }
}