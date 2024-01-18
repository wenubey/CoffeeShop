package com.wenubey.coffeeshop.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.wenubey.coffeeshop.data.local.entities.Feedback

@Dao
interface FeedbackDao : BaseDao<Feedback> {

    /**
     * Retrieves all feedback entries from the local database.
     *
     * @return A list of [Feedback] objects representing all feedback entries in the database.
     */
    @Query("SELECT * FROM feedback_table")
    suspend fun getAllFeedbacks(): List<Feedback>

    /**
     * Clears all feedback entries from the local database.
     */
    @Query("DELETE FROM feedback_table")
    suspend fun clearFeedbacks()
}