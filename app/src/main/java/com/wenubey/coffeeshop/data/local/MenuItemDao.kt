package com.wenubey.coffeeshop.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.sqlite.db.SupportSQLiteQuery
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.util.Constants.MENU_ITEM_TABLE_NAME




@Dao
interface MenuItemDao: BaseDao<MenuItem> {

    /**
     * Get all objects from database
     */
    @Query("SELECT * FROM menu_item_table")
    suspend fun getAllMenuItems(): List<MenuItem>

    /**
     *  Delete all from database
     */
    @Query("DELETE FROM menu_item_table")
    suspend fun deleteAllMenuItems()

}