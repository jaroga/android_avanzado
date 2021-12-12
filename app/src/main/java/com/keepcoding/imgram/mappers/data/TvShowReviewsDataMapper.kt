package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.MovieReviewsItemData
import com.keepcoding.imgram.model.data.TvShowReviewsItemData
import com.keepcoding.imgram.model.localdata.MovieReviewsItemLocalData
import com.keepcoding.imgram.model.localdata.TvShowReviewsItemLocalData
import com.keepcoding.imgram.model.network.MovieReviewsItemNetworkData
import com.keepcoding.imgram.model.network.TvShowReviewsItemNetworkData
import javax.inject.Inject

class TvShowReviewsDataMapper @Inject constructor() {

    private fun mapNetworkToData(network: TvShowReviewsItemNetworkData): TvShowReviewsItemData {
        return TvShowReviewsItemData(network.id, network.author, network.content, network.url)
    }

    fun mapNetworkToData(networkList: List<TvShowReviewsItemNetworkData>): List<TvShowReviewsItemData> {
        return networkList.map { mapNetworkToData(it) }
    }



    private fun mapLocalToData(local: TvShowReviewsItemLocalData): TvShowReviewsItemData {
        return TvShowReviewsItemData(local.id, local.author, local.content, local.url)
    }

    fun mapLocalToData(localList: List<TvShowReviewsItemLocalData>): List<TvShowReviewsItemData> {
        return localList.map { mapLocalToData(it) }
    }

    private fun mapDataToLocal(tvShowId: Int, local: TvShowReviewsItemData): TvShowReviewsItemLocalData {
        return TvShowReviewsItemLocalData(local.id, tvShowId.toLong(), local.author, local.content, local.url)
    }

    fun mapDataToLocal(movieId: Int, localList: List<TvShowReviewsItemData>): List<TvShowReviewsItemLocalData> {
        return localList.map { mapDataToLocal(movieId, it) }
    }
}