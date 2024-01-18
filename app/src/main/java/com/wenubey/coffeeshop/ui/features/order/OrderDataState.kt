package com.wenubey.coffeeshop.ui.features.order

import com.wenubey.coffeeshop.data.local.entities.Order

data class OrderDataState(
    val order: Order? = null,
    val error: String? = null,
)
