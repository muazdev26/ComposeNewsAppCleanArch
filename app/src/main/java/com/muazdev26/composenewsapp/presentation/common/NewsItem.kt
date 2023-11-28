package com.muazdev26.composenewsapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.presentation.Dimensions.NewsCardSize
import com.muazdev26.composenewsapp.presentation.Dimensions.SmallPadding

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (article: Article) -> Unit
) {
    Row(modifier = modifier.clickable { onClick(article) }) {
        AsyncImage(
            modifier = Modifier
                .size(NewsCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = article.urlToImage,
            contentDescription = "news Image",
            contentScale = ContentScale.Crop,
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = SmallPadding)
                .height(NewsCardSize)
        ) {
            Text(text = article.title, maxLines = 2, overflow = TextOverflow.Ellipsis)

            Row {
                Text(text = article.source.name, style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.width(SmallPadding))
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = null)
                Text(text = article.publishedAt, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}