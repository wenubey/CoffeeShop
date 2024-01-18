package com.wenubey.coffeeshop.domain

import androidx.lifecycle.LiveData
import com.wenubey.coffeeshop.data.local.entities.MenuItem

interface MenuItemRepository {

    suspend fun getAllMenuItems(): Result<List<MenuItem>>

    suspend fun getMenuItem(name: String): Result<MenuItem>

    suspend fun addMenuItem(menuItem: MenuItem): Result<String>

    suspend fun deleteMenuItem(menuItem: MenuItem): Result<String>

    suspend fun clearMenuItems(): Result<String>
}