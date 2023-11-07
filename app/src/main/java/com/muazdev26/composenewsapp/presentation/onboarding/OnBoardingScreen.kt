package com.muazdev26.composenewsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.muazdev26.composenewsapp.presentation.Dimensions.INDICATORS_SIZE
import com.muazdev26.composenewsapp.presentation.Dimensions.MEDIUM_PADDING
import com.muazdev26.composenewsapp.presentation.common.NewsButton
import com.muazdev26.composenewsapp.presentation.common.NewsTextButton
import com.muazdev26.composenewsapp.presentation.common.PageIndicator
import com.muazdev26.composenewsapp.presentation.onboarding.components.OnBoardingItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onBoardingEvent: (OnBoardingEvent) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {

        val pagerState = rememberPagerState(0) {
            onBoardingScreens.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf()
                }
            }
        }

        HorizontalPager(state = pagerState) {
            OnBoardingItem(onBoarding = onBoardingScreens[it])
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val scope = rememberCoroutineScope()
            PageIndicator(
                modifier = Modifier.width(INDICATORS_SIZE),
                pageSize = onBoardingScreens.size,
                selectedPage = pagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(buttonText = buttonState.value[0]) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                }

                NewsButton(buttonText = buttonState.value[1]) {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            onBoardingEvent(OnBoardingEvent.SavAppEntry)
                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(0.5f))
    }
}