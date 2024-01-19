package com.wenubey.coffeeshop.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme

@Composable
fun CommonTopAppBar(
    @StringRes title: Int,
    onNavigationIconClicked: () -> Unit,
) {
    CommonTopAppBarContent(
        title = title,
        onNavigationIconClicked = onNavigationIconClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommonTopAppBarContent(
   @StringRes title: Int = R.string.home_screen_title,
   onNavigationIconClicked: () -> Unit = {},

) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClicked,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = stringResource(R.string.this_button_open_the_drawer_menu)
                )
            }
        },
    )
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun CommonTopAppBarContentPreview() {
     CoffeeShopTheme {
        Surface {
             CommonTopAppBarContent()
        }
    }
}