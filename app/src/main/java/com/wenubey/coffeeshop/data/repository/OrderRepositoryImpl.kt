package com.wenubey.coffeeshop.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.wenubey.coffeeshop.data.local.dao.FeedbackDao
import com.wenubey.coffeeshop.data.local.dao.MenuItemDao
import com.wenubey.coffeeshop.data.local.dao.OrderDao
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order
import com.wenubey.coffeeshop.domain.CoffeeShopRepository

class CoffeeShopRepositoryImpl(
    private val menuItemDao: MenuItemDao,
    private val orderDao: OrderDao,
    private val feedbackDao: FeedbackDao,
) : CoffeeShopRepository {

    // Menu items related Operations
    override suspend fun getAllMenuItems(): Result<LiveData<List<MenuItem>>> {
        return try {
            val menuItems = menuItemDao.getAllMenuItems()
            Log.w(TAG, "getAllMenuItems:Success")
            Result.success(menuItems)
        } catch (e: Exception) {
            Log.e(TAG, "getAllMenuItems:Error ", e)
            Result.failure(e)
        }
    }

    override suspend fun getMenuItem(name: String): Result<LiveData<MenuItem>> {
        return try {
            val menuItem = menuItemDao.getMenuItem(name)
            Log.w(TAG, "getMenuItem:Success")
            Result.success(menuItem)
        } catch (e: Exception) {
            Log.e(TAG, "getMenuItem:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun addMenuItem(menuItem: MenuItem): Result<String> {
        return try {
            menuItemDao.insert(menuItem)
            Log.w(TAG, "addMenuItem:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "addMenuItem:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun deleteMenuItem(menuItem: MenuItem): Result<String> {
        return try {
            menuItemDao.delete(menuItem)
            Log.w(TAG, "deleteMenuItem:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "deleteMenuItem:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun clearMenuItems(): Result<String> {
        return try {
            menuItemDao.deleteAllMenuItems()
            Log.w(TAG, "clearMenuItems:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "clearMenuItems:Error", e)
            Result.failure(e)
        }
    }

    // Order related Operations

    override suspend fun clearOrderHistory(): Result<String> {
        return try {
            orderDao.deleteAllOrders()
            Log.w(TAG, "clearOrderHistory:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "clearOrderHistory:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun addOrder(order: Order): Result<String> {
        return try {
            orderDao.insert(order)
            Log.w(TAG, "addOrder:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "addOrder:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun deleteOrder(order: Order): Result<String> {
        return try {
            orderDao.delete(order)
            Log.w(TAG, "deleteOrder:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "deleteOrder:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun getOrder(id: String): Result<LiveData<Order>> {
        return try {
            val order = orderDao.getOrder(id)
            Log.w(TAG, "getOrder:Success")
            Result.success(order)
        } catch (e: Exception) {
            Log.e(TAG, "getOrder:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun getAllOrders(): Result<LiveData<List<Order>>> {
        return try {
            val orders = orderDao.getAllOrders()
            Log.w(TAG, "getAllOrders:Success")
            Result.success(orders)
        } catch (e: Exception) {
            Log.e(TAG, "getAllOrders:Error", e)
            Result.failure(e)
        }
    }

    // Feedback related operations

    override suspend fun addFeedback(feedback: Feedback): Result<String> {
        return try {
            feedbackDao.insert(feedback)
            Log.w(TAG, "addFeedBack:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "addFeedBack:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun clearFeedbacks(): Result<String> {
        return try {
            feedbackDao.clearFeedbacks()
            Log.w(TAG, "clearFeedbacks:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "clearFeedbacks:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun getAllFeedbacks(): Result<LiveData<List<Feedback>>> {
        return try {
            val feedbacks = feedbackDao.getAllFeedbacks()
            Log.w(TAG, "getAllFeedbacks:Success")
            Result.success(feedbacks)
        } catch (e: Exception) {
            Log.e(TAG, "getAllFeedbacks:Error", e)
            Result.failure(e)
        }
    }

    companion object {
        private const val TAG = "coffeeShopRepositoryImpl"
        private const val SUCCESS = "Operation successful"
    }
}