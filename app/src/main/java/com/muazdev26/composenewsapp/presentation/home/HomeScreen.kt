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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.muazdev26.composenewsapp.R
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.presentation.Dimensions
import com.muazdev26.composenewsapp.presentation.Dimensions.MediumPadding
import com.muazdev26.composenewsapp.presentation.Dimensions.SmallPadding
import com.muazdev26.composenewsapp.presentation.common.ArticlesList
import com.muazdev26.composenewsapp.presentation.common.Searchbar
import com.muazdev26.composenewsapp.presentation.navgraph.Route

@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding)
            .statusBarsPadding()
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_news_icon),
                contentDescription = "",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(SmallPadding),
            )
        }

        Spacer(modifier = Modifier.height(SmallPadding))

        Searchbar(
            modifier = Modifier,
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearchClick = { },
            onClick = {
                navigate(Route.SearchNewsRoute.route)
            })
        Spacer(modifier = Modifier.height(SmallPadding))

        ArticlesList(
            articles = articles,
            onClick = {
                navigate(Route.NewsDetailRoute.route)
            })
    }

}