package com.wenubey.coffeeshop.util

import android.content.Context
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.MenuItemType
import java.text.SimpleDateFormat
import java.util.Locale

fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(calendar.time)
}

fun Context.makeToast(message: String?) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

val fakeMenuItem = MenuItem(
    itemName = "Item Name",
    menuItemType = MenuItemType.DESSERTS,
    itemPrice = 0.0,
)

fun groupMenuItemsByType(menuItems: List<MenuItem>): Map<String, List<MenuItem>> {
    return menuItems.groupBy { it.menuItemType.name }
}

@Composable
fun getIconForMenuItemTypes(menuItemType: MenuItemType): ImageVector {
    return when(menuItemType) {
        MenuItemType.DESSERTS -> Icons.Default.Cake
        MenuItemType.DRINKS -> Icons.Default.Coffee
        MenuItemType.SNACKS -> Icons.Default.Fastfood
    }
}