package com.wenubey.coffeeshop.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.wenubey.coffeeshop.data.local.dao.OrderDao
import com.wenubey.coffeeshop.data.local.entities.Order
import com.wenubey.coffeeshop.domain.OrderRepository

class OrderRepositoryImpl(
    private val orderDao: OrderDao,
) : OrderRepository {


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



    companion object {
        private const val TAG = "coffeeShopRepositoryImpl"
        private const val SUCCESS = "Operation successful"
    }
}