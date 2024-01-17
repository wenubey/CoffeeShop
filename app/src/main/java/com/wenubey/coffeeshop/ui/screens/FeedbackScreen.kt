package com.wenubey.coffeeshop.ui.screens

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme

@Composable
fun FeedbackScreen() {
    FeedbackScreenContent()
}

@Composable
private fun FeedbackScreenContent() {

}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun FeedBackScreenContentPreview() {
     CoffeeShopTheme {
        Surface {
             FeedbackScreenContent()
        }
    }
}