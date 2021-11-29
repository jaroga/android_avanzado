package com.keepcoding.imgram.mappers

import com.keepcoding.imgram.model.TvShowItemData
import com.keepcoding.imgram.model.TvShowPresentation
import javax.inject.Inject

class TvShowPresentationMapper @Inject constructor() {

    fun mapDataToPresentation(data: List<TvShowItemData>): List<TvShowPresentation> {
        return data.map { mapDataToPresentation(it) }
    }

    fun mapDataToPresentation(data: TvShowItemData): TvShowPresentation {
        return TvShowPresentation(data.id, data.name, data.posterPath)
    }

    fun mapPresentationToData(presentation: TvShowPresentation): TvShowItemData {
        return TvShowItemData(presentation.id, presentation.name, presentation.posterPath)
    }
}