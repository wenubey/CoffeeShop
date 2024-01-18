package com.wenubey.coffeeshop.ui.features.order

import com.wenubey.coffeeshop.data.local.entities.Order

sealed class OrderEvent {

    // Order related operations
    data object OnClearOrderHistory: OrderEvent()

    data class OnAddOrder(val order: Order): OrderEvent()

    data class OnDeleteOrder(val order: Order): OrderEvent()

    data class OnGetOrder(val id: String): OrderEvent()

    data object OnGetAllOrders: OrderEvent()

}