package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.MovieDetailItemData
import com.keepcoding.imgram.model.data.TvShowDetailItemData
import com.keepcoding.imgram.model.localdata.MovieDetailItemLocalData
import com.keepcoding.imgram.model.localdata.TvShowDetailItemLocalData
import com.keepcoding.imgram.model.network.TvShowDetailItemNetworkData
import javax.inject.Inject

class TvShowDetailDataMapper @Inject constructor() {

    fun mapNetworkToData(local: TvShowDetailItemNetworkData): TvShowDetailItemData {
        return TvShowDetailItemData(local.id, local.name, local.posterPath, local.overview)
    }

    fun mapLocalToData(local: TvShowDetailItemLocalData): TvShowDetailItemData {
        return TvShowDetailItemData(local.id, local.name, local.posterPath, local.overview)
    }

    fun mapDataToLocal(local: TvShowDetailItemData): TvShowDetailItemLocalData {
        return TvShowDetailItemLocalData(local.id, local.id, local.name, local.posterPath, local.overview)
    }
}