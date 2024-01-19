package com.wenubey.coffeeshop.data.repository

import android.util.Log
import com.wenubey.coffeeshop.data.local.dao.FeedbackDao
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.domain.FeedbackRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of [FeedbackRepository] responsible for handling feedback-related operations.
 *
 * @param dao The DAO (Data Access Object) for interacting with the feedback data in the local database.
 * @param ioDispatcher The [CoroutineDispatcher] used for executing database operations in the IO thread.
 */
class FeedbackRepositoryImpl(
    private val dao: FeedbackDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): FeedbackRepository {

    /**
     * Adds a new feedback entry to the local database.
     *
     * @param feedback The [Feedback] object to be added.
     * @return A [Result] indicating the success or failure of the operation with a success message
     *         or an error message, respectively.
     */
    override suspend fun addFeedback(feedback: Feedback): Result<String> {
        return try {
            val result = withContext(ioDispatcher) {
                dao.insert(feedback)
                Log.w(TAG, "addFeedback:Success")
                SUCCESS
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "addFeedBack:Error", e)
            Result.failure(e)
        }
    }

    /**
     * Clears all feedback entries from the local database.
     *
     * @return A [Result] indicating the success or failure of the operation with a success message
     *         or an error message, respectively.
     */
    override suspend fun clearFeedbacks(): Result<String> {
        return try {
            val result = withContext(ioDispatcher) {
                dao.clearFeedbacks()
                Log.w(TAG, "clearFeedbacks:Success")
                SUCCESS
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "clearFeedbacks:Error", e)
            Result.failure(e)
        }
    }

    /**
     * Retrieves all feedback entries from the local database.
     *
     * @return A [Result] containing a list of [Feedback] objects if the operation is successful,
     *         or an error message if the operation fails.
     */
    override suspend fun getAllFeedbacks(): Result<List<Feedback>> {
        return try {
            val result = withContext(ioDispatcher) {
                val feedbacks = dao.getAllFeedbacks()
                Log.w(TAG, "getAllFeedbacks:Success")
                return@withContext feedbacks
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "getAllFeedbacks:Error", e)
            Result.failure(e)
        }
    }

    override suspend fun deleteFeedback(feedback: Feedback): Result<String> {
        return try {
            val result = withContext(ioDispatcher) {
                dao.delete(feedback)
                Log.w(TAG, "deleteFeedback:Success")
                SUCCESS
            }
            Result.success(result)
        } catch (e: Exception) {
            Log.e(TAG, "deleteFeedback:Error", e)
            Result.failure(e)
        }
    }

    companion object {
        private const val TAG = "feedbackRepositoryImpl"
        private const val SUCCESS = "Operation successful"
    }
}