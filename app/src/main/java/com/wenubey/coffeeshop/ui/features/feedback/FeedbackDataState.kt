package com.wenubey.coffeeshop.ui.features.feedback

import com.wenubey.coffeeshop.data.local.entities.Feedback

data class FeedbackDataState(
    val feedback: Feedback? = null,
    val feedbacks: List<Feedback>? = null,
    val message: String? = null,
    val error: String? = null,
)