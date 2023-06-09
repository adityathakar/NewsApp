package com.example.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.domain.model.Article
import com.example.newsapp.ui.screens.headlines.ArticlesScreen
import com.example.newsapp.ui.screens.saved.SavedArticlesScreen
import com.example.newsapp.ui.screens.sources.SourcesScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
    navHostController: NavHostController,
    onArticleClicked: (Article) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = BottomNavItem.Headlines.route
    ) {
        composable(BottomNavItem.Headlines.route) {
            ArticlesScreen(onArticleClicked = onArticleClicked)
        }
        composable(BottomNavItem.Sources.route) {
            SourcesScreen()
        }
        composable(BottomNavItem.Saved.route) {
            SavedArticlesScreen(onArticleClicked = onArticleClicked)
        }
    }
}