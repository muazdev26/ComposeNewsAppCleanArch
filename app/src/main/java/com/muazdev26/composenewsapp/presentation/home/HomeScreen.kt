package com.muazdev26.composenewsapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.muazdev26.composenewsapp.R
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.presentation.Dimensions.MediumPadding
import com.muazdev26.composenewsapp.presentation.Dimensions.SmallPadding
import com.muazdev26.composenewsapp.presentation.common.ArticlesList
import com.muazdev26.composenewsapp.presentation.common.Searchbar
import com.muazdev26.composenewsapp.presentation.navgraph.Route

@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearchScreen: () -> Unit,
    navigateToDetailsScreen: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_news_icon),
                contentDescription = "",
                modifier = Modifier.height(70.dp)
            )
        }

        Searchbar(
            modifier = Modifier,
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearchClick = { },
            onClick = {
                navigateToSearchScreen()
            })

        ArticlesList(
            articles = articles,
            onClick = {
                navigateToDetailsScreen(it)
            })
    }

}