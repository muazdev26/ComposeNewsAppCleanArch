package com.muazdev26.composenewsapp.presentation.details

import com.muazdev26.composenewsapp.domain.models.Article

sealed class DetailsEvents {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvents()
    data object RemovingSideEffects : DetailsEvents()
}