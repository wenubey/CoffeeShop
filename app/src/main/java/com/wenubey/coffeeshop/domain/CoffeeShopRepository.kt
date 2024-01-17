package com.wenubey.coffeeshop.domain

import androidx.lifecycle.LiveData
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order

interface CoffeeShopRepository {

    // Menu related operations

    suspend fun getAllMenuItems(): Result<LiveData<List<MenuItem>>>

    suspend fun getMenuItem(name: String): Result<LiveData<MenuItem>>

    suspend fun addMenuItem(menuItem: MenuItem): Result<String>

    suspend fun deleteMenuItem(menuItem: MenuItem): Result<String>

    suspend fun clearMenuItems(): Result<String>

    // Order related operations

    suspend fun clearOrderHistory(): Result<String>

    suspend fun addOrder(order: Order): Result<String>

    suspend fun deleteOrder(order: Order): Result<String>

    suspend fun getOrder(id: String): Result<LiveData<Order>>

    suspend fun getAllOrders(): Result<LiveData<List<Order>>>


    // Feedback related operations

    suspend fun addFeedback(feedback: Feedback): Result<String>

    suspend fun clearFeedbacks(): Result<String>

    suspend fun getAllFeedbacks(): Result<LiveData<List<Feedback>>>
}