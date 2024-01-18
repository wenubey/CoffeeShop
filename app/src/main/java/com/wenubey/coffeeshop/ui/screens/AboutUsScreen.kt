package com.wenubey.coffeeshop.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme

@Composable
fun AboutUsScreen() {
    AboutUsScreenContent()
}

@Composable
private fun AboutUsScreenContent() {
    Box(contentAlignment = Alignment.Center ) {
        Text("ABOUT US")
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun AboutUsScreenContentPreview() {
     CoffeeShopTheme {
        Surface {
             AboutUsScreenContent()
        }
    }
}