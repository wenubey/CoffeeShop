package com.wenubey.coffeeshop.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme

@Composable
fun ErrorScreen(
    error: String
) {
    ErrorScreenContent(error = error)
}

@Composable
private fun ErrorScreenContent(
     error: String = stringResource(id = R.string.order_history_list_empty_message),
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f),
            imageVector = Icons.Default.Error,
            contentDescription = stringResource(R.string.error_screen_description),
            tint = Color.Red.copy(alpha = 0.3f)
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = error,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun ErrorScreenContentPreview() {
     CoffeeShopTheme {
        Surface {
             ErrorScreenContent()
        }
    }
}