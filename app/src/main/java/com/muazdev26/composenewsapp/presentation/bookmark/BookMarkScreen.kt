package com.muazdev26.composenewsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.muazdev26.composenewsapp.R
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.presentation.Dimensions.SmallPadding
import com.muazdev26.composenewsapp.presentation.common.ArticlesList

@Composable
fun BookMarkScreen(
    bookMarkScreenState: BookMarkScreenState,
    navigateToDetailsScreen: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(SmallPadding)
    ) {

        Text(
            text = stringResource(id = R.string.bookmarks),
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(SmallPadding))

        ArticlesList(articles = bookMarkScreenState.bookmarkArticles, onClick = {
            navigateToDetailsScreen(it)
        })


    }

}