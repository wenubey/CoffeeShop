package com.wenubey.coffeeshop.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: String,
)

val items = listOf(
    NavigationItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        route = Screen.HomeScreen.route,
    ),
    NavigationItem(
        title = "About Us",
        selectedIcon = Icons.Filled.Info,
        unSelectedIcon = Icons.Outlined.Info,
        route = Screen.AboutUsScreen.route,
    ),
    NavigationItem(
        title = "Feedback",
        selectedIcon = Icons.Filled.Feedback,
        unSelectedIcon = Icons.Outlined.Feedback,
        route = Screen.FeedBackScreen.route,
    ),
    NavigationItem(
        title = "Order History",
        selectedIcon = Icons.Filled.Schedule,
        unSelectedIcon = Icons.Outlined.Schedule,
        route = Screen.OrderHistoryScreen.route,
    ),

)