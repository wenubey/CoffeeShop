package com.wenubey.coffeeshop.domain

import androidx.lifecycle.LiveData
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order

interface CoffeeShopRepository {

    // Menu related operations

    suspend fun getAllMenuItems(): Result<LiveData<List<MenuItem>>>

    suspend fun getMenuItem(name: String): Result<LiveData<MenuItem>>

    suspend fun addMenuItem(menuItem: MenuItem)

    suspend fun deleteMenuItem(menuItem: MenuItem)

    suspend fun clearMenuItems()

    // Order related operations

    suspend fun clearOrderHistory()

    suspend fun addOrder(order: Order)

    suspend fun deleteOrder(order: Order)

    suspend fun getOrder(id: String): Result<LiveData<Order>>

    suspend fun getAllOrders(): Result<LiveData<List<Order>>>


}