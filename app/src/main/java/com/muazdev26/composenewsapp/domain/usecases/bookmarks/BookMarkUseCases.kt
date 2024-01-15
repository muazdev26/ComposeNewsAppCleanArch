package com.muazdev26.composenewsapp.domain.usecases.bookmarks

data class BookMarkUseCases(
    val getBookMarksUseCase: GetBookMarksUseCase,
    val getSingleBookMarkUseCase: GetSingleBookMarkUseCase,
    val deleteBookMarkUseCase: DeleteBookMarkUseCase,
    val upsertBookMarkUseCase: UpsertBookMarkUseCase,
)