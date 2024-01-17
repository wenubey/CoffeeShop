package com.wenubey.coffeeshop.data.local

import androidx.room.Dao
import androidx.room.Query
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order

@Dao
interface OrderDao: BaseDao<Order> {

    /**
     * Get all objects from database
     */
    @Query("SELECT * FROM order_table")
    suspend fun getAllMenuItems(): List<MenuItem>

    /**
     *  Delete all from database
     */
    @Query("DELETE FROM order_table")
    suspend fun deleteAllMenuItems()
}