package com.muazdev26.composenewsapp.domain.usecases.home

import com.muazdev26.composenewsapp.domain.repositories.NewsRepository

class GetNewsUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>) = newsRepository.getNews(sources)
}