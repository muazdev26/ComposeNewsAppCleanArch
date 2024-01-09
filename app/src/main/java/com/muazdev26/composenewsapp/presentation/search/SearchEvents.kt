package com.muazdev26.composenewsapp.presentation.search

sealed class SearchEvents {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvents()
    data object SearchNews : SearchEvents()
}