package com.wenubey.coffeeshop.domain

import com.wenubey.coffeeshop.data.local.entities.Feedback

interface FeedbackRepository {

    suspend fun addFeedback(feedback: Feedback): Result<String>

    suspend fun clearFeedbacks(): Result<String>

    suspend fun getAllFeedbacks(): Result<List<Feedback>>

    suspend fun deleteFeedback(feedback: Feedback): Result<String>
}