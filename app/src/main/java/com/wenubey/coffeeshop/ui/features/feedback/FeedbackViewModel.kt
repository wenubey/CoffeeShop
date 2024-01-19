package com.wenubey.coffeeshop.ui.features.feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.domain.FeedbackRepository
import kotlinx.coroutines.launch

class FeedbackViewModel(
    private val repo: FeedbackRepository
): ViewModel() {

    private val _feedbackDataState = MutableLiveData<FeedbackDataState>()
    val feedbackDataState: LiveData<FeedbackDataState>
        get() = _feedbackDataState

    fun onFeedbackEvent(event: FeedbackEvent) {
        when (event) {
            is FeedbackEvent.OnClearFeedbacks -> clearFeedbacks()
            is FeedbackEvent.OnAddFeedback -> addFeedback(event.feedback)
            is FeedbackEvent.OnGetAllFeedbacks -> getAllFeedbacks()
            is FeedbackEvent.OnDeleteFeedback -> deleteFeedback(event.feedback)
        }
    }

    private fun deleteFeedback(feedback: Feedback) = viewModelScope.launch {
        val result = repo.deleteFeedback(feedback)
        if (result.isSuccess) {
            _feedbackDataState.postValue(FeedbackDataState(message = result.getOrNull()))
        } else {
            _feedbackDataState.postValue(FeedbackDataState(error = result.exceptionOrNull()?.message))
        }
    }

    fun getAllFeedbacks() = viewModelScope.launch {
        val result = repo.getAllFeedbacks()
        if (result.isSuccess) {
            _feedbackDataState.postValue(FeedbackDataState(feedbacks = result.getOrNull()))
        } else {
            _feedbackDataState.postValue(FeedbackDataState(error = result.exceptionOrNull()?.message))
        }
    }

    private fun addFeedback(feedback: Feedback) = viewModelScope.launch {
        val result = repo.addFeedback(feedback)
        if (result.isSuccess) {
            _feedbackDataState.postValue(FeedbackDataState(message = result.getOrNull()))
        } else {
            _feedbackDataState.postValue(FeedbackDataState(error = result.exceptionOrNull()?.message))
        }
    }

    private fun clearFeedbacks() = viewModelScope.launch {
        val result = repo.clearFeedbacks()
        if (result.isSuccess) {
            _feedbackDataState.postValue(FeedbackDataState(message = result.getOrNull()))
        } else {
            _feedbackDataState.postValue(FeedbackDataState(error = result.exceptionOrNull()?.message))
        }
    }
}