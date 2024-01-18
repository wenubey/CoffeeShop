package com.wenubey.coffeeshop.data.repository

import android.util.Log
import com.wenubey.coffeeshop.data.local.dao.OrderDao
import com.wenubey.coffeeshop.data.local.entities.Order
import com.wenubey.coffeeshop.domain.OrderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of [OrderRepository] responsible for handling order-related operations.
 *
 * @param orderDao The DAO (Data Access Object) for interacting with the order data in the local database.
 * @param ioDispatcher The [CoroutineDispatcher] used for executing database operations in the IO thread.
 */
class OrderRepositoryImpl(
    private val orderDao: OrderDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : OrderRepository {

    /**
     * Clears the entire order history by deleting all order entries from the local database.
     *
     * @return A [Result] indicating the success or failure of the operation with a success message
     *         or an error message, respectively.
     */
    override suspend fun clearOrderHistory(): Result<String> {
        return try {
            val result = withContext(ioDispatcher) {
                orderDao.deleteAllOrders()
                Log.w(TAG, "clearOrderHistory:Success")
                SUCCESS
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "clearOrderHistory:Error", e)
            Result.failure(e)
        }
    }

    /**
     * Adds a new order entry to the local database.
     *
     * @param order The [Order] object to be added.
     * @return A [Result] indicating the success or failure of the operation with a success message
     *         or an error message, respectively.
     */
    override suspend fun addOrder(order: Order): Result<String> {
        return try {
            val result = withContext(ioDispatcher) {
                orderDao.insert(order)
                Log.w(TAG, "addOrder:Success")
                SUCCESS
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "addOrder:Error", e)
            Result.failure(e)
        }
    }

    /**
     * Deletes a specific order entry from the local database.
     *
     * @param order The [Order] object to be deleted.
     * @return A [Result] indicating the success or failure of the operation with a success message
     *         or an error message, respectively.
     */
    override suspend fun deleteOrder(order: Order): Result<String> {
        return try {
            val result = withContext(ioDispatcher) {
                orderDao.delete(order)
                Log.w(TAG, "deleteOrder:Success")
                SUCCESS
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "deleteOrder:Error", e)
            Result.failure(e)
        }
    }

    /**
     * Retrieves a specific order entry by its ID from the local database.
     *
     * @param id The ID of the order to retrieve.
     * @return A [Result] containing an [Order] object if the operation is successful,
     *         or an error message if the operation fails.
     */
    override suspend fun getOrder(id: String): Result<Order> {
        return try {
            val result = withContext(ioDispatcher) {
                val order = orderDao.getOrder(id)
                Log.w(TAG, "getOrder:Success")
                return@withContext order
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "getOrder:Error", e)
            Result.failure(e)
        }
    }

    /**
     * Retrieves all order entries from the local database.
     *
     * @return A [Result] containing a [List] of [Order] objects if the operation is successful,
     *         or an error message if the operation fails.
     */
    override suspend fun getAllOrders(): Result<List<Order>> {
        return try {
            val result = withContext(ioDispatcher) {
                val orders = orderDao.getAllOrders()
                Log.w(TAG, "getAllOrders:Success")
                return@withContext orders
            }
            Result.success(result)
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