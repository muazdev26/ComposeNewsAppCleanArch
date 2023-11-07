package com.muazdev26.composenewsapp.data.remote.dto


import com.muazdev26.composenewsapp.domain.models.Article

data class NewsDTO(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)