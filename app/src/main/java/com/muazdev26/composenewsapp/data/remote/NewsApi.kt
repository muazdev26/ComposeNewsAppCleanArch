package com.muazdev26.composenewsapp.data.remote

import com.muazdev26.composenewsapp.data.remote.dto.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
    ): NewsDTO

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
    ): NewsDTO
}