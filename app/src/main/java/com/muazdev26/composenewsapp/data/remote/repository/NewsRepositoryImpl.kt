package com.muazdev26.composenewsapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.muazdev26.composenewsapp.data.remote.NewsApi
import com.muazdev26.composenewsapp.data.remote.paging.NewsPagingSource
import com.muazdev26.composenewsapp.domain.repositories.NewsRepository

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {

    override fun getNews(sources: List<String>) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            NewsPagingSource(newsApi, sources.joinToString(","))
        }
    ).flow
}