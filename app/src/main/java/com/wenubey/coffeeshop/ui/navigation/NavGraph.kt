package com.wenubey.coffeeshop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wenubey.coffeeshop.ui.screens.AboutUsScreen
import com.wenubey.coffeeshop.ui.screens.FeedBackScreen
import com.wenubey.coffeeshop.ui.screens.HomeScreen
import com.wenubey.coffeeshop.ui.screens.OrderHistoryScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }

        composable(route = Screen.OrderHistoryScreen.route) {
            OrderHistoryScreen()
        }

        composable(route = Screen.AboutUsScreen.route) {
            AboutUsScreen()
        }

        composable(route = Screen.FeedBackScreen.route) {
            FeedBackScreen()
        }
    }
}