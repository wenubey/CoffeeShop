package com.wenubey.coffeeshop.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeDeleteCard(
    onSwiped: () -> Unit,
    dismissContent: @Composable() (RowScope.() -> Unit)
) {
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                onSwiped()
                true
            } else {
                false
            }
        },
    )
    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> MaterialTheme.colorScheme.background
            DismissValue.DismissedToStart -> Color.Red
            DismissValue.DismissedToEnd -> Color.Transparent
        }, label = stringResource(R.string.swipe_to_dismiss_background_color_label)
    )
    SwipeToDismiss(
        state = dismissState,
        background = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = color.copy(alpha = 0.5f),
                    )
            ) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterEnd),
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = R.string.delete_confirmation_title)
                )
            }
        },
        dismissContent = dismissContent,
    )
}
