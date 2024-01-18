package com.wenubey.coffeeshop.ui

import com.wenubey.coffeeshop.data.local.entities.MenuItem

data class MenuItemsDataState(
    val menuItems: List<MenuItem>? = null,
    val error: String? = null,
)
