package com.wenubey.coffeeshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wenubey.coffeeshop.data.local.dao.FeedbackDao
import com.wenubey.coffeeshop.data.local.dao.MenuItemDao
import com.wenubey.coffeeshop.data.local.dao.OrderDao
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order

@Database(
    entities = [MenuItem::class, Order::class, Feedback::class],
    version = 1
)
@TypeConverters(CoffeeShopTypeConverter::class)
abstract class CoffeeShopDatabase : RoomDatabase() {
    abstract val menuItemDao: MenuItemDao
    abstract val orderDao: OrderDao
    abstract val feedbackDao: FeedbackDao
}