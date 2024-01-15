@file:OptIn(ExperimentalMaterial3Api::class)

package com.muazdev26.composenewsapp.presentation.details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.muazdev26.composenewsapp.R

@Composable
fun DetailsTopBar(
    onShareClick: () -> Unit,
    onSeeMoreClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onBackClick: () -> Unit,
) {

    TopAppBar(
        title = { Text("Details") },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.run {
            mediumTopAppBarColors(
                containerColor = Color.Transparent,
                actionIconContentColor = colorResource(id = R.color.body),
                navigationIconContentColor = colorResource(id = R.color.body),
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(painterResource(id = R.drawable.ic_back), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onBookMarkClick() }) {
                Icon(painterResource(id = R.drawable.ic_bookmark), contentDescription = null)
            }
            IconButton(onClick = { onShareClick() }) {
                Icon(painterResource(id = R.drawable.ic_share), contentDescription = null)
            }
            IconButton(onClick = { onSeeMoreClick() }) {
                Icon(painterResource(id = R.drawable.ic_see_more), contentDescription = null)
            }
        }
    )

}