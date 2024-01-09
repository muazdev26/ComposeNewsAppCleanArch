package com.muazdev26.composenewsapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.muazdev26.composenewsapp.data.remote.NewsApi
import com.muazdev26.composenewsapp.data.remote.paging.NewsPagingSource
import com.muazdev26.composenewsapp.data.remote.paging.SearchPagingSource
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {

    override fun getNews(sources: List<String>) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            NewsPagingSource(newsApi, sources.joinToString(","))
        }
    ).flow

    override fun searchNews(query: String, sources: List<String>) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            SearchPagingSource(newsApi, query, sources.joinToString(","))
        }
    ).flow
}