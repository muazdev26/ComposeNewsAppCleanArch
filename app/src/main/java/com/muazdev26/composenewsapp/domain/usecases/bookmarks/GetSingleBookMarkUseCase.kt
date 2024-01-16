package com.muazdev26.composenewsapp.domain.usecases.bookmarks

import com.muazdev26.composenewsapp.domain.repositories.NewsRepository

class GetSingleBookMarkUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String) = newsRepository.getSingBookMark(url)
}