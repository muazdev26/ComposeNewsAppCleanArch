package com.muazdev26.composenewsapp.domain.repositories

import androidx.paging.PagingData
import com.muazdev26.composenewsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>>
    fun getBookMarks(): Flow<List<Article>?>
    suspend fun getSingBookMark(url: String): Article?
    suspend fun upsertBookMark(article: Article)
    suspend fun deleteBookMark(article: Article)
}