package com.muazdev26.composenewsapp.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.muazdev26.composenewsapp.presentation.Dimensions.MediumPadding
import com.muazdev26.composenewsapp.presentation.onboarding.OnBoarding
import com.muazdev26.composenewsapp.presentation.onboarding.onBoardingScreens


@Composable
fun OnBoardingItem(
    modifier: Modifier = Modifier,
    onBoarding: OnBoarding
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = onBoarding.image),
            contentDescription = stringResource(
                id = onBoarding.title
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(horizontal = MediumPadding)
        )
        Spacer(modifier = Modifier.height(MediumPadding))
        Text(
            text = stringResource(id = onBoarding.title), fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(
                MediumPadding
            )
        )
        Text(
            text = stringResource(id = onBoarding.description),
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(
                horizontal =
                MediumPadding
            )
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingItemPreview() {
    OnBoardingItem(onBoarding = onBoardingScreens[0])
}