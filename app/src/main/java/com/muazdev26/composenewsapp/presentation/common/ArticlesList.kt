package com.muazdev26.composenewsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.presentation.Dimensions.MediumPadding
import com.muazdev26.composenewsapp.presentation.Dimensions.SmallPadding
import com.muazdev26.composenewsapp.util.parseErrorMessage

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val result = HandlePagingResult(articles = articles)
    if (result) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding),
            contentPadding = PaddingValues(SmallPadding)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    NewsItem(article = article, onClick = {
                        onClick(article)
                    })
                }
            }
        }
    }
}

@Composable
fun HandlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            NoData(message = error.parseErrorMessage(LocalContext.current))
            false
        }

        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MediumPadding)
    ) {
        repeat(10) {
            NewsItemShimmer(Modifier.padding(MediumPadding))
        }
    }
}