package com.muazdev26.composenewsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.presentation.Dimensions.MediumPadding
import com.muazdev26.composenewsapp.presentation.common.ArticlesList
import com.muazdev26.composenewsapp.presentation.common.Searchbar

@Composable
fun SearchScreen(
    searchState: SearchState,
    events: (SearchEvents) -> Unit,
    onNewsClick: (article: Article) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(MediumPadding)
            .statusBarsPadding()
    ) {
        Searchbar(modifier = Modifier,
            text = searchState.query,
            readOnly = false,
            onValueChange = {
                events(SearchEvents.UpdateSearchQuery(it))
            },
            onSearchClick = { events(SearchEvents.SearchNews) })

        Spacer(Modifier.height(MediumPadding))
        searchState.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = { article ->
                onNewsClick(article)
            })
        }

    }

}