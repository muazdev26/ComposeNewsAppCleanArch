package com.muazdev26.composenewsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.muazdev26.composenewsapp.presentation.onboarding.OnBoardingScreen
import com.muazdev26.composenewsapp.presentation.onboarding.OnBoardingViewModel

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
                Text(text = "News Screen")
            }
        }
    }

}