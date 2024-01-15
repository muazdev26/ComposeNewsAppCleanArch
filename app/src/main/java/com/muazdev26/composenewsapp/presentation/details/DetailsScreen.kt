package com.muazdev26.composenewsapp.presentation.details

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.muazdev26.composenewsapp.R
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.presentation.Dimensions
import com.muazdev26.composenewsapp.presentation.details.components.DetailsTopBar

@Composable
fun DetailsScreen(
    article: Article,
    events: (DetailsEvents) -> Unit,
    onSeeMoreClick: () -> Unit,
    navigateUp: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onShareClick = {
                Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_TEXT, "${article.title}\n\nRead more at:\n${article.url}")
                    type = "text/plain"
                    if (resolveActivity(context.packageManager) != null) {
                        context.startActivity(this)
                    }
                }
            },
            onSeeMoreClick = { onSeeMoreClick() },
            onBookMarkClick = { events(DetailsEvents.UpsertDeleteArticle) }) {
            navigateUp()
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(Dimensions.SmallPadding)
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = "Article Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimensions.ImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(Dimensions.MediumPadding))

                Text(
                    text = article.title, style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ), color = colorResource(id = R.color.text_title)
                )

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.text_title)
                )
            }

        }
    }

}