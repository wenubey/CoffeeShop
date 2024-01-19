package com.wenubey.coffeeshop.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.wenubey.coffeeshop.R

@Composable
fun DeleteAllFeedbacksAlertDialog(
    isDialogOpened: MutableState<Boolean> = mutableStateOf(false),
    onConfirmButtonClicked: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = { isDialogOpened.value = false },
        confirmButton = {
            Button(onClick = onConfirmButtonClicked) {
                Text(text = stringResource(id = R.string.delete_button))
            }
        },
        dismissButton = {
            Button(onClick = { isDialogOpened.value = false }) {
                Text(text = stringResource(id = R.string.cancel_button))
            }
        },
        text = {
            Text(text = stringResource(R.string.delete_all_feedbacks_message))
        },
        title = {
            Text(text = stringResource(R.string.delete_all_feedbacks_title))
        },
    )
}