package com.muazdev26.composenewsapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.muazdev26.composenewsapp.R
import com.muazdev26.composenewsapp.presentation.Dimensions.MediumPadding
import com.muazdev26.composenewsapp.presentation.Dimensions.SmallPadding

@Composable
fun NoData(
    message: String,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding)
    ) {

        Image(
            painter = painterResource(id = R.drawable.no_data_internet),
            contentDescription = ""
        )
        Text(
            text = message,
            modifier = Modifier.padding(top = SmallPadding),
            fontWeight = FontWeight.SemiBold
        )

    }

}

@Preview(showSystemUi = true)
@Composable
fun NoDatePreview() {
    NoData(message = "error")
}