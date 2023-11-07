package com.muazdev26.composenewsapp.presentation.onboarding

sealed class OnBoardingEvent {

    data object SavAppEntry : OnBoardingEvent()
}