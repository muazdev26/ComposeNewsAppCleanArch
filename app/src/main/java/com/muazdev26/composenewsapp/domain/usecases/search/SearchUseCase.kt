package com.muazdev26.composenewsapp.domain.usecases.search

import com.muazdev26.composenewsapp.domain.repositories.NewsRepository

class SearchUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(query: String, sources: List<String>) =
        newsRepository.searchNews(query, sources)
}