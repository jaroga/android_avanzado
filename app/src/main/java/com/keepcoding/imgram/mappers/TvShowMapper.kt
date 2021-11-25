package com.keepcoding.imgram.mappers

import com.keepcoding.imgram.model.TvShowPresentation
import com.keepcoding.imgram.model.TvShowItemData
import javax.inject.Inject

class TvShowMapper @Inject constructor() {

    fun mapDataToPresentation(data: List<TvShowItemData>): List<TvShowPresentation> {
        return data.map { mapDataToPresentation(it) }
    }

    fun mapDataToPresentation(data: TvShowItemData): TvShowPresentation {
        return TvShowPresentation(data.id, data.name, data.posterPath)
    }
}