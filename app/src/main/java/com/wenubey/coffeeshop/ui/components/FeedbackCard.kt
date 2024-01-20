package com.wenubey.coffeeshop.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
) {
    var isExpanded by remember { mutableStateOf(false) }

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
                Icon(
                    imageVector = getIconForFeedbackOpinion(feedbackOpinion = feedback.feedbackOpinion),
                    contentDescription = stringResource(
                        id = R.string.feedback_opinion_description
                    ),
                    tint = getTintForFeedbackOpinion(feedbackOpinion = feedback.feedbackOpinion)
                )
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