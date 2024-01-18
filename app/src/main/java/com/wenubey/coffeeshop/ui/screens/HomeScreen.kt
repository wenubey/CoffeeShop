package com.wenubey.coffeeshop.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.MenuItemType
import com.wenubey.coffeeshop.ui.components.MenuItemAddAlertDialog
import com.wenubey.coffeeshop.ui.features.menu_item.MenuItemDataState
import com.wenubey.coffeeshop.ui.features.menu_item.MenuItemEvent
import com.wenubey.coffeeshop.ui.features.menu_item.MenuItemViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(drawerState: DrawerState) {
    HomeScreenContent(
        drawerState = drawerState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    drawerState: DrawerState = DrawerState(initialValue = DrawerValue.Closed),
    menuViewModel: MenuItemViewModel = koinViewModel(),
) {
    val menuItemsDataState by menuViewModel.menuItemDataState.observeAsState(initial = MenuItemDataState())
    val coroutineScope = rememberCoroutineScope()
    var isDialogOpen = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(menuItemsDataState) {
        menuViewModel.getAllMenuItems()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.home_screen_title),
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = stringResource(R.string.this_button_open_the_drawer_menu)
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { isDialogOpen.value = !isDialogOpen.value }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_menu_item_to_database_description)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            val menuItems = menuItemsDataState.menuItems
            if (!menuItems.isNullOrEmpty()) {
                LazyColumn {
                    items(menuItems) { menuItem ->
                        Card {
                            Text(text = menuItem.itemName)
                            Text(text = menuItem.menuItemType.name)
                            Text(text = menuItem.itemPrice.toString())
                        }
                    }
                }
            }
        }
    }
    if (isDialogOpen.value) {
        MenuItemAddAlertDialog(
            isDialogOpen = isDialogOpen,
            onDialogConfirmedClicked = {
                menuViewModel.onMenuItemEvent(MenuItemEvent.OnAddMenuItem(it))
            },
        )
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun HomeScreenContentPreview() {
    com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme {
        Surface {
            HomeScreenContent()
        }
    }
}

