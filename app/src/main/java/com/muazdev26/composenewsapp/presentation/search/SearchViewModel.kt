package com.muazdev26.composenewsapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.muazdev26.composenewsapp.domain.usecases.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    fun onSearchEvent(searchEvents: SearchEvents) {
        when (searchEvents) {
            is SearchEvents.SearchNews -> {
                searchNews()
            }

            is SearchEvents.UpdateSearchQuery -> {
                _searchState.update {
                    it.copy(query = searchEvents.searchQuery)
                }
            }
        }
    }

    private fun searchNews() {
        val searchedNews = searchUseCase.invoke(
            searchState.value.query,
            listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        _searchState.value = _searchState.value.copy(articles = searchedNews)
    }
}