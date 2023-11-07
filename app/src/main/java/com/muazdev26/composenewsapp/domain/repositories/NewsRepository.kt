package com.muazdev26.composenewsapp.domain.repositories

import androidx.paging.PagingData
import com.muazdev26.composenewsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}