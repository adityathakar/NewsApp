package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.Article
import com.example.newsapp.ui.navigation.AppNavHost
import com.example.newsapp.ui.navigation.BottomNavItem
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val screens = listOf(
                BottomNavItem.Headlines,
                BottomNavItem.Sources,
                BottomNavItem.Saved
            )
            val navController = rememberNavController()
            val backStackEntry = navController.currentBackStackEntryAsState()

            val onArticleClicked : (Article) -> Unit = {
                startActivity(WebViewActivity.newIntent(this, it))
            }

            NewsAppTheme {
                Scaffold(bottomBar = {
                    NavigationBar {
                        screens.forEach { screen ->
                            NavigationBarItem(selected = screen.route == backStackEntry.value?.destination?.route,
                                onClick = { navController.navigate(screen.route) },
                                label = {
                                    Text(text = screen.title)
                                },
                                icon = {
                                    Icon(imageVector = screen.icon, contentDescription = "")
                                })
                        }
                    }

                }) {
                    AppNavHost(
                        modifier = Modifier.padding(it),
                        navHostController = navController,
                        onArticleClicked = onArticleClicked,
                    )
                }
            }
        }
    }
}