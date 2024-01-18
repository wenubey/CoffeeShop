package com.wenubey.coffeeshop.domain

import androidx.lifecycle.LiveData
import com.wenubey.coffeeshop.data.local.entities.Order

interface OrderRepository {

    suspend fun clearOrderHistory(): Result<String>

    suspend fun addOrder(order: Order): Result<String>

    suspend fun deleteOrder(order: Order): Result<String>

    suspend fun getOrder(id: String): Result<Order>

    suspend fun getAllOrders(): Result<List<Order>>

}