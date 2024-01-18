package com.wenubey.coffeeshop.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wenubey.coffeeshop.ui.screens.AboutUsScreen
import com.wenubey.coffeeshop.ui.screens.FeedbackScreen
import com.wenubey.coffeeshop.ui.screens.HomeScreen
import com.wenubey.coffeeshop.ui.screens.OrderHistoryScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    drawerState: DrawerState,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(drawerState = drawerState)
        }

        composable(route = Screen.OrderHistoryScreen.route) {
            OrderHistoryScreen()
        }

        composable(route = Screen.AboutUsScreen.route) {
            AboutUsScreen()
        }

        composable(route = Screen.FeedBackScreen.route) {
            FeedbackScreen()
        }
    }
}