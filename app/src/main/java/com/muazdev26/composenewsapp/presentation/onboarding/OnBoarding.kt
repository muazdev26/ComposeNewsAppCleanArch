package com.muazdev26.composenewsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.muazdev26.composenewsapp.R

data class OnBoarding(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)

val onBoardingScreens = listOf(
    OnBoarding(
        R.string.title_1,
        R.string.desc_1,
        R.drawable.on_boarding_1
    ),

    OnBoarding(
        R.string.title_2,
        R.string.desc_2,
        R.drawable.on_boarding_2
    ),

    OnBoarding(
        R.string.title_3,
        R.string.desc_3,
        R.drawable.on_boarding_3
    )
)
