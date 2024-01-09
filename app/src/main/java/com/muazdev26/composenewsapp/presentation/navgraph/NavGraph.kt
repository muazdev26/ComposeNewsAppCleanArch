package com.muazdev26.composenewsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.muazdev26.composenewsapp.presentation.home.HomeScreen
import com.muazdev26.composenewsapp.presentation.home.HomeViewModel
import com.muazdev26.composenewsapp.presentation.onboarding.OnBoardingScreen
import com.muazdev26.composenewsapp.presentation.onboarding.OnBoardingViewModel
import com.muazdev26.composenewsapp.presentation.search.SearchEvents
import com.muazdev26.composenewsapp.presentation.search.SearchScreen
import com.muazdev26.composenewsapp.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String,
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel
) {

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            startDestination = Route.OnBoardingRoute.route,
            route = Route.AppStartNavigation.route
        ) {
            composable(route = Route.OnBoardingRoute.route) {
                OnBoardingScreen(onBoardingEvent = onBoardingViewModel::onEvent)
            }
        }

        navigation(
            route = Route.MainNavigation.route,
            startDestination = Route.HomeRoute.route
        ) {
            composable(route = Route.HomeRoute.route) {
                val homeViewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    searchState = homeViewModel.searchState.value,
                    events = homeViewModel::onSearchEvent,
                    onNewsClick = {}
                )
//                val news = homeViewModel.news.collectAsLazyPagingItems()
//                HomeScreen(articles = news, navigate = {})
            }
        }
    }

}