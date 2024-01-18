package com.wenubey.coffeeshop.ui.features.feedback

import com.wenubey.coffeeshop.data.local.entities.Feedback

sealed class FeedbackEvent {

    data object OnClearFeedbacks: FeedbackEvent()

    data class OnAddFeedback(val feedback: Feedback): FeedbackEvent()

    data object OnGetAllFeedbacks: FeedbackEvent()
}