package com.muazdev26.composenewsapp.domain.usecases.bookmarks

import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.domain.repositories.NewsRepository

class DeleteBookMarkUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) = newsRepository.deleteBookMark(article)
}