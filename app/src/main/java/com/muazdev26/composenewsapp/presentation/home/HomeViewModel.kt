package com.muazdev26.composenewsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.muazdev26.composenewsapp.domain.usecases.home.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsUseCase: NewsUseCases
) : ViewModel() {

    val news = newsUseCase.getNewsUseCase(listOf("bbc-news", "abc-news", "al-jazeera-english"))
        .cachedIn(viewModelScope)
}