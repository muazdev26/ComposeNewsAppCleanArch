package com.muazdev26.composenewsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muazdev26.composenewsapp.domain.usecases.bookmarks.BookMarkUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val bookMarkUseCases: BookMarkUseCases
) : ViewModel() {

     var sideEffects by mutableStateOf<String?>(null)
        private set

    fun onEvent(events: DetailsEvents) {
        when (events) {
            is DetailsEvents.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = bookMarkUseCases.getSingleBookMarkUseCase(events.article.id)
                    sideEffects = if (article == null) {
                        bookMarkUseCases.upsertBookMarkUseCase(events.article)
                        "Article Bookmarked"
                    } else {
                        bookMarkUseCases.deleteBookMarkUseCase(events.article)
                        "Article deleted from bookmarks"
                    }
                }
            }

            DetailsEvents.RemovingSideEffects -> {
                sideEffects = null
            }
        }
    }
}