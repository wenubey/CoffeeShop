package com.wenubey.coffeeshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order

@Database(
    entities = [MenuItem::class, Order::class],
    version = 1
)
@TypeConverters(CoffeeShopTypeConverter::class)
abstract class CoffeeShopDatabase : RoomDatabase() {
    abstract val coffeeShopDao: CoffeeShopDao
}