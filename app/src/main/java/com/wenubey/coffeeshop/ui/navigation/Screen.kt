package com.wenubey.coffeeshop.ui.navigation

sealed class Screen(val route: String) {
    data object HomeScreen: Screen(route = "homeScreen")

    data object OrderHistoryScreen: Screen(route = "orderHistoryScreen")

    data object AboutUsScreen: Screen(route = "aboutUsScreen")

    data object FeedBackScreen: Screen(route = "feedBackScreen")

}