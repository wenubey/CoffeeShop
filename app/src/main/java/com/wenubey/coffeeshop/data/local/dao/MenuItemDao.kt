package com.wenubey.coffeeshop.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.wenubey.coffeeshop.data.local.entities.MenuItem


@Dao
interface MenuItemDao: BaseDao<MenuItem> {

    /**
     * Retrieves all menu items from the database.
     *
     * @return A list of [MenuItem] objects containing all menu items stored in the database.
     */
    @Query("SELECT * FROM menu_item_table")
    suspend fun getAllMenuItems(): List<MenuItem>

    /**
     * Retrieves a menu item from the database that matches the given name, using a partial name match.
     *
     * @param name The name or part of the name to search for within menu items.
     * @return A [MenuItem] object that matches the provided name (or partial name).
     */
    @Query("SELECT * FROM menu_item_table WHERE itemName LIKE  '%' || :name || '%'")
    suspend fun getMenuItem(name: String): MenuItem

    /**
     *  Deletes all menu items from the database.
     */
    @Query("DELETE FROM menu_item_table")
    suspend fun deleteAllMenuItems()

}