package com.wenubey.coffeeshop.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.data.local.entities.FeedbackOpinion
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme
import com.wenubey.coffeeshop.util.getIconForFeedbackOpinion
import com.wenubey.coffeeshop.util.getTintForFeedbackOpinion

@Composable
fun AddFeedbackAlertDialog(
    isFeedbackAddDialogOpen: MutableState<Boolean> = mutableStateOf(true),
    onConfirmButtonClicked: (feedback: Feedback) -> Unit = {},
) {
    var currentFeedbackContent by remember {
        mutableStateOf("")
    }
    var selectedFeedbackOpinion by remember {
        mutableStateOf(FeedbackOpinion.POSITIVE)
    }
    AlertDialog(
        onDismissRequest = { isFeedbackAddDialogOpen.value = false },
        confirmButton = {
            Button(
                onClick = {
                    val newFeedback = Feedback(
                        feedbackOpinion = selectedFeedbackOpinion,
                        feedBackContent = currentFeedbackContent,
                    )
                    onConfirmButtonClicked(newFeedback)
                },
            ) {
                Text(text = stringResource(R.string.send_button))
            }
        },
        title = {
            Column {
                Text(
                    text = stringResource(R.string.add_feedback),
                    style = MaterialTheme.typography.titleMedium,
                )
                Divider(thickness = 1.dp)
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.opinion_pick_title),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    FeedbackOpinion.entries.forEach { feedbackOpinion ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                selected = selectedFeedbackOpinion == feedbackOpinion,
                                onClick = { selectedFeedbackOpinion = feedbackOpinion })
                            Icon(
                                imageVector = getIconForFeedbackOpinion(feedbackOpinion = feedbackOpinion),
                                tint = getTintForFeedbackOpinion(feedbackOpinion = feedbackOpinion),
                                contentDescription = stringResource(id = R.string.feedback_opinion_description)
                            )
                        }

                    }
                }
                OutlinedTextField(
                    value = currentFeedbackContent,
                    onValueChange = { currentFeedbackContent = it },
                    placeholder = { Text(stringResource(R.string.feedback_placeholder)) }
                )

            }
        }
    )
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun AddFeedbackAlertDialogPreview() {
    CoffeeShopTheme {
        Surface {
            AddFeedbackAlertDialog()
        }
    }
}