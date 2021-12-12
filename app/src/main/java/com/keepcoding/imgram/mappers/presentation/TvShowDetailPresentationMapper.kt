package com.keepcoding.imgram.mappers.presentation

import com.keepcoding.imgram.model.data.*
import com.keepcoding.imgram.model.presentation.*
import javax.inject.Inject

class TvShowDetailPresentationMapper @Inject constructor() {

    fun mapDataToPresentation(data: List<TvShowReviewsItemData>): List<TvShowReviewsPresentation> {
        return data.map { TvShowReviewsPresentation(it.id, it.author, it.content, it.url) }
    }

    fun mapDataToPresentation(tvShowDetail: TvShowDetailItemData, tvShowReviews: List<TvShowReviewsItemData> ): TvShowDetailPresentation {
        return TvShowDetailPresentation(tvShowDetail.id, tvShowDetail.name, tvShowDetail.posterPath,
            tvShowDetail.overview, mapDataToPresentation(tvShowReviews))
    }

    fun mapPresentationToData(presentation: TvShowPresentation): TvShowItemData {
        return TvShowItemData(presentation.id, presentation.name, presentation.posterPath)
    }
}