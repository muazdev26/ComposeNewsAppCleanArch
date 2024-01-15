package com.muazdev26.composenewsapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.muazdev26.composenewsapp.data.local.NewsDao
import com.muazdev26.composenewsapp.data.mapper.toArticle
import com.muazdev26.composenewsapp.data.mapper.toArticleEntity
import com.muazdev26.composenewsapp.data.remote.NewsApi
import com.muazdev26.composenewsapp.data.remote.paging.NewsPagingSource
import com.muazdev26.composenewsapp.data.remote.paging.SearchPagingSource
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
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

    override fun getBookMarks() =
        newsDao.getAllArticles().map { articleEntities ->
            articleEntities?.map { it.toArticle() }
        }

    override suspend fun upsertBookMark(article: Article) {
        withContext(dispatcher) {
            newsDao.upsertBookMark(article.toArticleEntity())
        }
    }

    override fun getSingBookMark(id: Long): Article? {
        return newsDao.getArticle(id)?.toArticle()
    }

    override suspend fun deleteBookMark(article: Article) {
        withContext(dispatcher) {
            newsDao.deleteArticle(article.toArticleEntity())
        }
    }
}