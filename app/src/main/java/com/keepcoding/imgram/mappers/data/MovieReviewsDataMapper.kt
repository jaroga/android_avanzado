package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.data.MovieReviewsItemData
import com.keepcoding.imgram.model.localdata.MovieItemLocalData
import com.keepcoding.imgram.model.localdata.MovieReviewsItemLocalData
import com.keepcoding.imgram.model.network.MovieReviewsItemNetworkData
import javax.inject.Inject

class MovieReviewsDataMapper @Inject constructor() {

    private fun mapLocalToData(local: MovieReviewsItemLocalData): MovieReviewsItemData {
        return MovieReviewsItemData(local.id, local.author, local.content, local.url)
    }

    fun mapLocalToData(localList: List<MovieReviewsItemLocalData>): List<MovieReviewsItemData> {
        return localList.map { mapLocalToData(it) }
    }

    private fun mapNetworkToData(network: MovieReviewsItemNetworkData): MovieReviewsItemData {
        return MovieReviewsItemData(network.id, network.author, network.content, network.url)
    }

    fun mapNetworkToData(networkList: List<MovieReviewsItemNetworkData>): List<MovieReviewsItemData> {
        return networkList.map { mapNetworkToData(it) }
    }

    private fun mapDataToLocal(movieId: Int, local: MovieReviewsItemData): MovieReviewsItemLocalData {
        return MovieReviewsItemLocalData(local.id, movieId.toLong(), local.author, local.content, local.url)
    }

    fun mapDataToLocal(movieId: Int, localList: List<MovieReviewsItemData>): List<MovieReviewsItemLocalData> {
        return localList.map { mapDataToLocal(movieId, it) }
    }
}