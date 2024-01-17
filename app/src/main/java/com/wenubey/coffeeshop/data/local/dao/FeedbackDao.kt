package com.wenubey.coffeeshop.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.wenubey.coffeeshop.data.local.entities.Feedback

@Dao
interface FeedbackDao: BaseDao<Feedback> {

    @Query("SELECT * FROM feedback_table")
    suspend fun getAllFeedbacks(): LiveData<List<Feedback>>

    @Query("DELETE FROM feedback_table")
    suspend fun clearFeedbacks()
}