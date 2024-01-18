package com.wenubey.coffeeshop.ui.features.menu_item

import com.wenubey.coffeeshop.data.local.entities.MenuItem

data class MenuItemDataState(
    val menuItem: MenuItem? = null,
    val message: String? = null,
    val error: String? = null,
)