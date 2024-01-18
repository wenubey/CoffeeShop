package com.wenubey.coffeeshop.ui.features.order

import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order

sealed class OrderEvent {

    // Order related operations
    data object OnClearOrderHistory: OrderEvent()

    data object OnAddOrder: OrderEvent()

    data class OnDeleteOrder(val order: Order): OrderEvent()

    data class OnGetOrder(val id: String): OrderEvent()

    data object OnGetAllOrders: OrderEvent()

    data class OnAddMenuItem(val menuItem: MenuItem): OrderEvent()

}