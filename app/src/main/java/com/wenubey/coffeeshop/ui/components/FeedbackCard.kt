package com.wenubey.coffeeshop.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme
import com.wenubey.coffeeshop.util.fakeFeedback
import com.wenubey.coffeeshop.util.getIconForFeedbackOpinion
import com.wenubey.coffeeshop.util.getTintForFeedbackOpinion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackCard(
    feedback: Feedback = fakeFeedback,
    onDeleteFeedbackClicked: (feedback: Feedback) -> Unit = {},
) {
    var isExpanded by remember { mutableStateOf(false) }
    val isDeleteDialogOpened = remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        onClick = {
            isExpanded = !isExpanded
        }
    ) {
        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.Start) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = feedback.feedbackCreatedAt)
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(
                        imageVector = getIconForFeedbackOpinion(feedbackOpinion = feedback.feedbackOpinion),
                        contentDescription = stringResource(
                            id = R.string.feedback_opinion_description
                        ),
                        tint = getTintForFeedbackOpinion(feedbackOpinion = feedback.feedbackOpinion)
                    )
                    Icon(
                        modifier = Modifier.clickable { isDeleteDialogOpened.value = true },
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.feedback_delete_description)
                    )
                }
            }
            Divider(thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedback.feedBackContent,
                maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
    if (isDeleteDialogOpened.value) {
        DeleteFeedbackAlertDialog(
            isDialogOpened = isDeleteDialogOpened,
            onDeleteOperationConfirmed = {
                onDeleteFeedbackClicked(feedback)
            }
        )
    }
}

@Composable
fun DeleteFeedbackAlertDialog(
    isDialogOpened: MutableState<Boolean> = mutableStateOf(false),
    onDeleteOperationConfirmed: () -> Unit = {},

    ) {
    AlertDialog(
        onDismissRequest = {
            isDialogOpened.value = false
        },
        confirmButton = {
            Button(onClick = onDeleteOperationConfirmed) {
                Text(text = stringResource(R.string.delete_button))
            }
        },
        text = {
            Text(text = stringResource(R.string.delete_operation_message))
        },
        dismissButton = {
            Button(onClick = { isDialogOpened.value = false }) {
                Text(text = stringResource(R.string.cancel_button))
            }
        },
        title = {
            Text(
                text = stringResource(R.string.delete_confirmation_title),
                style = MaterialTheme.typography.titleLarge
            )
        },
    )
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun FeedBackCardPreview() {
    CoffeeShopTheme {
        Surface {
            FeedbackCard()
        }
    }
}