package com.muazdev26.composenewsapp.domain.usecases.bookmarks

import com.muazdev26.composenewsapp.domain.repositories.NewsRepository

class GetBookMarksUseCase(
    private val newsRepository: NewsRepository
) {

    operator fun invoke() = newsRepository.getBookMarks()
}