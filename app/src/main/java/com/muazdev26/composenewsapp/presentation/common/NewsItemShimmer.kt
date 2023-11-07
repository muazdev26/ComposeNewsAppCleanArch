package com.muazdev26.composenewsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.muazdev26.composenewsapp.presentation.Dimensions
import com.muazdev26.composenewsapp.presentation.Dimensions.SmallPadding

@Composable
fun NewsItemShimmer(
    modifier: Modifier
) {

    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(Dimensions.NewsCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = SmallPadding)
                .height(Dimensions.NewsCardSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SmallPadding)
                    .height(30.dp)
                    .shimmerEffect(),
            )

            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(SmallPadding)
                        .height(15.dp)
                        .shimmerEffect(),
                )
            }
        }
    }

}