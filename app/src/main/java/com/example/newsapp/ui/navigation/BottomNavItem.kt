package com.example.newsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {

    object Headlines: BottomNavItem("Headlines", Icons.Filled.Home, "headlines")

    object Sources: BottomNavItem("Sources", Icons.Filled.Add, "sources")

    object Saved: BottomNavItem("Saved", Icons.Filled.Favorite, "saved")
}