package com.wenubey.coffeeshop.ui

import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order

sealed class CoffeeShopEvent {



    // Order related operations
    data object OnClearOrderHistory: CoffeeShopEvent()

    data class OnAddOrder(val order: Order): CoffeeShopEvent()

    data class OnDeleteOrder(val order: Order): CoffeeShopEvent()

    data class OnGetOrder(val id: String): CoffeeShopEvent()

    data object OnGetAllOrders: CoffeeShopEvent()

    // Feedback related operations

    data object OnClearFeedbacks: CoffeeShopEvent()

    data class OnAddFeedback(val feedback: Feedback): CoffeeShopEvent()

    data object OnGetAllFeedbacks: CoffeeShopEvent()

}