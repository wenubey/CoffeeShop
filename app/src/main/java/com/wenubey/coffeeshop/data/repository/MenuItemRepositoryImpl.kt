package com.wenubey.coffeeshop.data.repository

import android.util.Log
import com.wenubey.coffeeshop.data.local.dao.MenuItemDao
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.domain.MenuItemRepository

class MenuItemRepositoryImpl(
    private val dao: MenuItemDao
): MenuItemRepository {
    override suspend fun getAllMenuItems(): Result<List<MenuItem>> {
        return try {
            val menuItems = dao.getAllMenuItems()
            Log.w(TAG, "getAllMenuItems:Success")
            Result.success(menuItems)
        } catch (e: Exception) {
            Log.e(TAG, "getAllMenuItems:Error ", e)
            Result.failure(e)
        }
    }

    override suspend fun getMenuItem(name: String): Result<MenuItem> {
        return try {
            val menuItem = dao.getMenuItem(name)
            Log.w(TAG, "getMenuItem:Success")
            Result.success(menuItem)
        } catch (e: Exception) {
            Log.e(TAG, "getMenuItem:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun addMenuItem(menuItem: MenuItem): Result<String> {
        return try {
            dao.insert(menuItem)
            Log.w(TAG, "addMenuItem:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "addMenuItem:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun deleteMenuItem(menuItem: MenuItem): Result<String> {
        return try {
            dao.delete(menuItem)
            Log.w(TAG, "deleteMenuItem:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "deleteMenuItem:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun clearMenuItems(): Result<String> {
        return try {
            dao.deleteAllMenuItems()
            Log.w(TAG, "clearMenuItems:Success")
            Result.success(SUCCESS)
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