package com.muazdev26.composenewsapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muazdev26.composenewsapp.data.remote.NewsApi
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.util.Consts
import com.muazdev26.composenewsapp.util.Consts.DEFAULT_PAGE
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: DEFAULT_PAGE
        return try {
            val newsResponse = newsApi.getNews(page, Consts.API_KEY, sources)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount != newsResponse.totalResults) page + 1 else null,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}