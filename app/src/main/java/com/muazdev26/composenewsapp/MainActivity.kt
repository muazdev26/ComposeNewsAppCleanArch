package com.muazdev26.composenewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.muazdev26.composenewsapp.presentation.navgraph.NavGraph
import com.muazdev26.composenewsapp.presentation.onboarding.OnBoardingScreen
import com.muazdev26.composenewsapp.presentation.onboarding.OnBoardingViewModel
import com.muazdev26.composenewsapp.presentation.ui.theme.ComposeNewsAppCleanArchTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val onBoardingViewModel by viewModels<OnBoardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.splashCondition
            }
        }
        setContent {
            ComposeNewsAppCleanArchTheme {

                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val startDestination = mainViewModel.startDestination
                    NavGraph(
                        startDestination = startDestination,
                        navController = rememberNavController(),
                        onBoardingViewModel = onBoardingViewModel
                    )

//                    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
//                    OnBoardingScreen(onBoardingEvent = onBoardingViewModel::onEvent)
                }
            }
        }
    }
}
