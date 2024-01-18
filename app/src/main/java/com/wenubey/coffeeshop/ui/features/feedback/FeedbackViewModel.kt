package com.wenubey.coffeeshop.ui.features.feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.domain.FeedbackRepository

class FeedbackViewModel(
    private val repo: FeedbackRepository
): ViewModel() {

    private val _feedbackDataState = MutableLiveData<FeedbackDataState>()
    val feedbackDataState: LiveData<FeedbackDataState>
        get() = _feedbackDataState

    fun onEvent(event: FeedbackEvent) {
        when (event) {
            is FeedbackEvent.OnClearFeedbacks -> clearFeedbacks()
            is FeedbackEvent.OnAddFeedback -> addFeedback(event.feedback)
            is FeedbackEvent.OnGetAllFeedbacks -> getAllFeedbacks()
        }
    }

    private fun getAllFeedbacks() {
        TODO("Not yet implemented")
    }

    private fun addFeedback(feedback: Feedback) {
        TODO("Not yet implemented")
    }

    private fun clearFeedbacks() {
        TODO("Not yet implemented")
    }
}