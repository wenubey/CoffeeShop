package com.wenubey.coffeeshop.ui

import com.wenubey.coffeeshop.data.local.entities.Order

data class OrderDataState(
    val order: Order? = null,
    val error: String? = null,
)
