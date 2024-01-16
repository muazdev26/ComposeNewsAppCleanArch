@file:OptIn(ExperimentalMaterial3Api::class)

package com.muazdev26.composenewsapp.presentation.news_navigator

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.muazdev26.composenewsapp.R
import com.muazdev26.composenewsapp.domain.models.Article
import com.muazdev26.composenewsapp.presentation.bookmark.BookMarkScreen
import com.muazdev26.composenewsapp.presentation.bookmark.BookMarkViewModel
import com.muazdev26.composenewsapp.presentation.details.DetailsEvents
import com.muazdev26.composenewsapp.presentation.details.DetailsScreen
import com.muazdev26.composenewsapp.presentation.details.DetailsViewModel
import com.muazdev26.composenewsapp.presentation.home.HomeScreen
import com.muazdev26.composenewsapp.presentation.home.HomeViewModel
import com.muazdev26.composenewsapp.presentation.navgraph.Route
import com.muazdev26.composenewsapp.presentation.news_navigator.components.AppBottomNavigation
import com.muazdev26.composenewsapp.presentation.news_navigator.components.BottomNavigationItem
import com.muazdev26.composenewsapp.presentation.search.SearchScreen
import com.muazdev26.composenewsapp.presentation.search.SearchViewModel
import com.muazdev26.composenewsapp.util.Consts.ARTICLE_KEY

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(R.drawable.ic_news, R.string.latest),
            BottomNavigationItem(R.drawable.ic_search, R.string.search),
            BottomNavigationItem(R.drawable.ic_bookmarks_collection, R.string.bookmarks)
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember(backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeRoute.route -> 0
            Route.SearchNewsRoute.route -> 1
            Route.SavedNewsRoute.route -> 2
            else -> 0
        }
    }

    val isBottomBarShow = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeRoute.route ||
                backStackState?.destination?.route == Route.SearchNewsRoute.route ||
                backStackState?.destination?.route == Route.SavedNewsRoute.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarShow) {
                AppBottomNavigation(
                    items = bottomNavigationItems,
                    selectedIndex = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> {
                                navigateToTab(navController, Route.HomeRoute.route)
                            }

                            1 -> {
                                navigateToTab(navController, Route.SearchNewsRoute.route)
                            }

                            2 -> {
                                navigateToTab(navController, Route.SavedNewsRoute.route)
                            }
                        }

                    })
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController, startDestination = Route.HomeRoute.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(Route.HomeRoute.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigateToSearchScreen = {
                    navigateToTab(navController, Route.SearchNewsRoute.route)
                }, navigateToDetailsScreen = { article ->
                    navigateToDetails(navController, article)
                })
            }

            composable(Route.SearchNewsRoute.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val searchState = viewModel.searchState.collectAsState()
                SearchScreen(
                    searchState = searchState.value,
                    events = viewModel::onSearchEvent,
                    onNewsClick = { article ->
                        navigateToDetails(navController, article)
                    })
            }

            composable(Route.SavedNewsRoute.route) {
                val viewModel: BookMarkViewModel = hiltViewModel()
                BookMarkScreen(
                    bookMarkScreenState = viewModel.bookMarkState.value,
                    navigateToDetailsScreen = { article ->
                        navigateToDetails(navController, article)
                    })
            }

            composable(Route.NewsDetailRoute.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.toastMessage != null) {
                    Toast.makeText(LocalContext.current, viewModel.toastMessage, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvents.RemovingSideEffects)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>(
                    ARTICLE_KEY
                )?.let { article ->
                    DetailsScreen(article = article, events = viewModel::onEvent, onSeeMoreClick = {

                    }) {
                        navController.navigateUp()
                    }
                }
            }

        }
    }

}

fun navigateToTab(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

fun navigateToDetails(
    navController: NavController,
    article: Article
) {
    navController.currentBackStackEntry?.savedStateHandle?.set(ARTICLE_KEY, article)
    navController.navigate(Route.NewsDetailRoute.route)
}
