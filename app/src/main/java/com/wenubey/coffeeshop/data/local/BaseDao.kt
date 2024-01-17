package com.wenubey.coffeeshop.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

interface BaseDao<T> {

    /**
     * Insert an object in the database
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T)

    /**
     * Insert an array of object in the database
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)

    /**
     * Update an object from the database
     *
     * @param obj the object to be updated.
     */
    @Update
    suspend fun update(obj: T)

    /**
     * Delete an object from database
     *
     * @param obj the object to be deleted
     */
    @Delete
    suspend fun delete(obj: T)

}