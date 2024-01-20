package com.wenubey.coffeeshop.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.ui.components.AddFeedbackAlertDialog
import com.wenubey.coffeeshop.ui.components.CommonTopAppBar
import com.wenubey.coffeeshop.ui.components.DeleteAllFeedbacksAlertDialog
import com.wenubey.coffeeshop.ui.components.ErrorScreen
import com.wenubey.coffeeshop.ui.components.FeedbackCard
import com.wenubey.coffeeshop.ui.components.SwipeDeleteCard
import com.wenubey.coffeeshop.ui.features.feedback.FeedbackDataState
import com.wenubey.coffeeshop.ui.features.feedback.FeedbackEvent
import com.wenubey.coffeeshop.ui.features.feedback.FeedbackViewModel
import com.wenubey.coffeeshop.util.makeToast
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeedbackScreen(drawerState: DrawerState) {
    FeedbackScreenContent(drawerState = drawerState)
}

@Composable
private fun FeedbackScreenContent(
    drawerState: DrawerState = DrawerState(initialValue = DrawerValue.Closed),
    viewModel: FeedbackViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val isFeedbackAddDialogOpen = remember {
        mutableStateOf(false)
    }
    val isDeleteAllFeedbacksDialogOpened = remember {
        mutableStateOf(false)
    }
    val feedbackDataState by viewModel.feedbackDataState.observeAsState(initial = FeedbackDataState())
    val context = LocalContext.current
    LaunchedEffect(feedbackDataState.message) {
        if (feedbackDataState.message != null) {
            context.makeToast(feedbackDataState.message)
        }
    }
    LaunchedEffect(feedbackDataState) {
        viewModel.getAllFeedbacks()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonTopAppBar(
                title = R.string.feedback_screen_title,
                onNavigationIconClicked = {
                    scope.launch {
                        drawerState.open()
                    }
                },
            )
        },
        floatingActionButton = {
            Row {
                FloatingActionButton(
                    onClick = {
                        isDeleteAllFeedbacksDialogOpened.value =
                            !isDeleteAllFeedbacksDialogOpened.value
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_all_feedbacks_description)
                    )
                }
                FloatingActionButton(
                    onClick = {
                        isFeedbackAddDialogOpen.value = !isFeedbackAddDialogOpen.value
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_feedback_description)
                    )
                }
            }

        },
    ) { paddingValues ->
        val feedbacks = feedbackDataState.feedbacks
        if (!feedbacks.isNullOrEmpty()) {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(feedbacks) { feedback ->
                    SwipeDeleteCard(
                        onSwiped = {
                            viewModel.onFeedbackEvent(FeedbackEvent.OnDeleteFeedback(feedback))
                        },
                        dismissContent = {
                            FeedbackCard(
                                feedback = feedback
                            )
                        }
                    )
                }
            }
        } else {
            ErrorScreen(error = stringResource(R.string.feedback_list_empty_message))
        }
    }
    if (isFeedbackAddDialogOpen.value) {
        AddFeedbackAlertDialog(
            isFeedbackAddDialogOpen = isFeedbackAddDialogOpen,
            onConfirmButtonClicked = {
                viewModel.onFeedbackEvent(FeedbackEvent.OnAddFeedback(it))
                isFeedbackAddDialogOpen.value = false
            },
        )
    }
    if (isDeleteAllFeedbacksDialogOpened.value) {
        DeleteAllFeedbacksAlertDialog(
            isDialogOpened = isDeleteAllFeedbacksDialogOpened,
            onConfirmButtonClicked = {
                viewModel.onFeedbackEvent(FeedbackEvent.OnClearFeedbacks)
                isDeleteAllFeedbacksDialogOpened.value = false
            }
        )
    }
}







