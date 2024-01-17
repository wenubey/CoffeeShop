package com.wenubey.coffeeshop.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wenubey.coffeeshop.util.Constants.MENU_ITEM_TABLE_NAME

@Entity(tableName = MENU_ITEM_TABLE_NAME)
data class MenuItem(
    @PrimaryKey
    @ColumnInfo(name = "itemId")
    val itemId: Int,
    @ColumnInfo(name = "itemName")
    val itemName: String,
    @ColumnInfo(name = "menuItemType")
    val menuItemType: MenuItemType,
    @ColumnInfo(name = "itemPrice")
    val itemPrice: Double,
)

enum class MenuItemType {
    DRINKS,
    DESSERTS,
    SNACKS
}
