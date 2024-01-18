package com.wenubey.coffeeshop.data.repository

import android.util.Log
import com.wenubey.coffeeshop.data.local.dao.MenuItemDao
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.domain.MenuItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of [MenuItemRepository] responsible for handling menu item-related operations.
 *
 * @param dao The DAO (Data Access Object) for interacting with the menu item data in the local database.
 * @param ioDispatcher The [CoroutineDispatcher] used for executing database operations in the IO thread.
 */
class MenuItemRepositoryImpl(
    private val dao: MenuItemDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MenuItemRepository {

    /**
     * Retrieves all menu items from the local database.
     *
     * @return A [Result] containing a [List] of [MenuItem] objects if the operation is successful,
     *         or an error message if the operation fails.
     */
    override suspend fun getAllMenuItems(): Result<List<MenuItem>> {
        return try {
            val result = withContext(ioDispatcher) {
                val menuItems = dao.getAllMenuItems()
                Log.w(TAG, "getAllMenuItems:Success")
                return@withContext menuItems
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "getAllMenuItems:Error ", e)
            Result.failure(e)
        }
    }

    /**
     * Retrieves a menu item by its name from the local database.
     *
     * @param name The name of the menu item to retrieve.
     * @return A [Result] containing a [MenuItem] object if the operation is successful,
     *         or an error message if the operation fails.
     */
    override suspend fun getMenuItem(name: String): Result<MenuItem> {
        return try {
            val result = withContext(ioDispatcher) {
                val menuItem = dao.getMenuItem(name)
                Log.w(TAG, "getMenuItem:Success")
                return@withContext menuItem
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "getMenuItem:Error", e)
            Result.failure(e)
        }
    }

    /**
     * Adds a new menu item to the local database.
     *
     * @param menuItem The [MenuItem] object to be added.
     * @return A [Result] indicating the success or failure of the operation with a success message
     *         or an error message, respectively.
     */
    override suspend fun addMenuItem(menuItem: MenuItem): Result<String> {
        return try {
            val result = withContext(ioDispatcher) {
                dao.insert(menuItem)
                Log.w(TAG, "addMenuItem:Success")
                SUCCESS
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "addMenuItem:Error", e)
            Result.failure(e)
        }
    }

    /**
     * Deletes a menu item from the local database.
     *
     * @param menuItem The [MenuItem] object to be deleted.
     * @return A [Result] indicating the success or failure of the operation with a success message
     *         or an error message, respectively.
     */
    override suspend fun deleteMenuItem(menuItem: MenuItem): Result<String> {
        return try {
            val result = withContext(ioDispatcher) {
                dao.delete(menuItem)
                Log.w(TAG, "deleteMenuItem:Success")
                SUCCESS
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "deleteMenuItem:Error", e)
            Result.failure(e)
        }
    }

    /**
     * Clears all menu items from the local database.
     *
     * @return A [Result] indicating the success or failure of the operation with a success message
     *         or an error message, respectively.
     */
    override suspend fun clearMenuItems(): Result<String> {
        return try {
            val result = withContext(ioDispatcher) {
                dao.deleteAllMenuItems()
                Log.w(TAG, "clearMenuItems:Success")
                SUCCESS
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "clearMenuItems:Error", e)
            Result.failure(e)
        }
    }

    companion object {
        private const val TAG = "menuItemRepositoryImpl"
        private const val SUCCESS = "Operation successful"
    }

}