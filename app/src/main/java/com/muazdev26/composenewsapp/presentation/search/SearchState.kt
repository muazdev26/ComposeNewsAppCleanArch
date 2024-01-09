package com.muazdev26.composenewsapp.presentation.search

import androidx.paging.PagingData
import com.muazdev26.composenewsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val query: String = "",
    val articles: Flow<PagingData<Article>>? = null
)