package com.wenubey.coffeeshop.ui.screens

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.ui.components.CommonTopAppBar
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme
import kotlinx.coroutines.launch

@Composable
fun AboutUsScreen(
    drawerState: DrawerState
) {
    AboutUsScreenContent(drawerState = drawerState)
}

@Composable
private fun AboutUsScreenContent(
    drawerState: DrawerState = DrawerState(initialValue = DrawerValue.Closed)
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = R.string.about_us_title,
                onNavigationIconClicked = {
                    scope.launch {
                        drawerState.open()
                    }
                },
            )
        },
    ) { paddingValues ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = stringResource(
                        R.string.app_icon
                    )
                )
                Text(
                    text = stringResource(R.string.welcome_app_message),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                Divider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(4.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.coffee_shop_about_us_message)
                    )
                    Divider(thickness = 1.dp)
                    ContactInfoRow(imageVector = Icons.Default.LocationOn, content = R.string.coffee_shop_address)
                    ContactInfoRow(imageVector = Icons.Default.Phone, content = R.string.coffee_shop_phone)
                    ContactInfoRow(imageVector = Icons.Default.Mail, content = R.string.coffee_shop_mail)
                }

            }
        }
    }
}

@Composable
fun ContactInfoRow(
    imageVector: ImageVector,
    @StringRes content: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = stringResource(R.string.contact_info_description)
        )
        Text(text = stringResource(id = content))
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