package com.wenubey.coffeeshop.ui.features.menu_item

import com.wenubey.coffeeshop.data.local.entities.MenuItem

sealed class MenuItemEvent {

    data class OnGetMenuItem(val name: String): MenuItemEvent()

    data class OnAddMenuItem(val menuItem: MenuItem): MenuItemEvent()

    data class OnDeleteMenuItem(val menuItem: MenuItem): MenuItemEvent()

    data object OnClearMenuItems: MenuItemEvent()
}