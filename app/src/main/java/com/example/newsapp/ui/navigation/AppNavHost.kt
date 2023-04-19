package com.example.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.ui.screens.headlines.HeadlinesScreen
import com.example.newsapp.ui.screens.saved.SavedScreen
import com.example.newsapp.ui.screens.sources.SourcesScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = BottomNavItem.Headlines.route
    ) {
        composable(BottomNavItem.Headlines.route) {
            HeadlinesScreen()
        }
        composable(BottomNavItem.Sources.route) {
            SourcesScreen()
        }
        composable(BottomNavItem.Saved.route) {
            SavedScreen()
        }
    }
}