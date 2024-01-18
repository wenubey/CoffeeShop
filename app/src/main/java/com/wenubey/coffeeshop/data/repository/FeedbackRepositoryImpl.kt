package com.wenubey.coffeeshop.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.wenubey.coffeeshop.data.local.dao.FeedbackDao
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.domain.FeedbackRepository

class FeedbackRepositoryImpl(
    private val dao: FeedbackDao
): FeedbackRepository {

    override suspend fun addFeedback(feedback: Feedback): Result<String> {
        return try {
            dao.insert(feedback)
            Log.w(TAG, "addFeedBack:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "addFeedBack:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun clearFeedbacks(): Result<String> {
        return try {
            dao.clearFeedbacks()
            Log.w(TAG, "clearFeedbacks:Success")
            Result.success(SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, "clearFeedbacks:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun getAllFeedbacks(): Result<LiveData<List<Feedback>>> {
        return try {
            val feedbacks = dao.getAllFeedbacks()
            Log.w(TAG, "getAllFeedbacks:Success")
            Result.success(feedbacks)
        } catch (e: Exception) {
            Log.e(TAG, "getAllFeedbacks:Error", e)
            Result.failure(e)
        }
    }

    companion object {
        private const val TAG = "feedbackRepositoryImpl"
        private const val SUCCESS = "Operation successful"
    }
}