package com.wenubey.coffeeshop.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.wenubey.coffeeshop.data.local.entities.Order

@Dao
interface OrderDao: BaseDao<Order> {

    /**
     * Retrieves all orders from the database.
     *
     * @return A list of [Order] objects containing all menu items stored in the database.
     */
    @Query("SELECT * FROM order_table")
    suspend fun getAllOrders(): LiveData<List<Order>>

    /**
     * Retrieves a order from the database that matches the given id, using a partial id match.
     *
     * @param id The id or part of the id to search for within order items.
     * @return A [Order] object that matches the provided id (or partial id).
     */
    @Query("SELECT * FROM order_table WHERE orderId LIKE  '%' || :id || '%'")
    suspend fun getOrder(id: String): LiveData<Order>

    /**
     *  Deletes all orders from the database.
     */
    @Query("DELETE FROM order_table")
    suspend fun deleteAllOrders()
}