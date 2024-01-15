package com.muazdev26.composenewsapp.presentation.bookmark

import com.muazdev26.composenewsapp.domain.models.Article

data class BookMarkScreenState(
     val bookmarkArticles: List<Article> = emptyList()
)