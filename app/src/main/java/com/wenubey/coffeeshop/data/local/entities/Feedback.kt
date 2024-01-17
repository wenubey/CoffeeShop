package com.wenubey.coffeeshop.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wenubey.coffeeshop.util.Constants.FEEDBACK_TABLE_NAME
import com.wenubey.coffeeshop.util.getCurrentTime
import java.util.UUID

@Entity(tableName = FEEDBACK_TABLE_NAME)
data class Feedback(
    @PrimaryKey
    @ColumnInfo("feedbackId")
    val feedbackId: String = UUID.randomUUID().toString(),
    @ColumnInfo("feedbackContent")
    val feedBackContent: String,
    @ColumnInfo("feedbackOpinion")
    val feedbackOpinion: FeedbackOpinion,
    @ColumnInfo("feedbackCreatedAt")
    val feedbackCreatedAt: String = getCurrentTime(),
)

enum class FeedbackOpinion {
    POSITIVE,
    NEGATIVE,
}
