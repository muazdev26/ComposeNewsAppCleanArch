package com.muazdev26.composenewsapp.presentation.navgraph

sealed class Route(
    val route: String
) {
    data object OnBoardingRoute : Route("onBoardingScreen")
    data object HomeRoute : Route("homeScreen")
    data object SavedNewsRoute : Route("savedNewsScreen")
    data object SearchNewsRoute : Route("searchNewsScreen")
    data object NewsDetailRoute : Route("newsDetailScreen")
    data object WebViewRoute : Route("webViewScreen")

    data object AppStartNavigation : Route("appStartNavigation")
    data object MainNavigation : Route("homeNavigation")


}
