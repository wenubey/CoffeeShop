package com.wenubey.coffeeshop.ui.features.feedback

import com.wenubey.coffeeshop.data.local.entities.Feedback

data class FeedbackDataState(
    val feedback: Feedback? = null,
    val error: String? = null,
)