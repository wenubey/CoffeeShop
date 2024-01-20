package com.wenubey.coffeeshop.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.ui.components.CommonTopAppBar
import com.wenubey.coffeeshop.ui.components.CurrentOrderAlertDialog
import com.wenubey.coffeeshop.ui.components.MenuItemAddAlertDialog
import com.wenubey.coffeeshop.ui.features.menu_item.MenuItemDataState
import com.wenubey.coffeeshop.ui.features.menu_item.MenuItemEvent
import com.wenubey.coffeeshop.ui.features.menu_item.MenuItemViewModel
import com.wenubey.coffeeshop.ui.features.order.OrderDataState
import com.wenubey.coffeeshop.ui.features.order.OrderEvent
import com.wenubey.coffeeshop.ui.features.order.OrderViewModel
import com.wenubey.coffeeshop.util.getIconForMenuItemTypes
import com.wenubey.coffeeshop.util.groupMenuItemsByType
import com.wenubey.coffeeshop.util.makeToast
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    drawerState: DrawerState,
) {
    HomeScreenContent(
        drawerState = drawerState,
    )
}

@Composable
private fun HomeScreenContent(
    drawerState: DrawerState = DrawerState(initialValue = DrawerValue.Closed),
    menuViewModel: MenuItemViewModel = koinViewModel(),
    orderViewModel: OrderViewModel = koinViewModel(),
) {
    val menuItemsDataState by menuViewModel.menuItemDataState.observeAsState(initial = MenuItemDataState())

    val currentOrder by orderViewModel.orderDataState.observeAsState(initial = OrderDataState())
    val currentTotalPrice = currentOrder.order?.totalPrice
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isMenuItemAddDialogOpen = remember {
        mutableStateOf(false)
    }
    val isCurrentOrderDialogOpen = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(menuItemsDataState) {
        menuViewModel.getAllMenuItems()
    }

    LaunchedEffect(currentOrder.message) {
        if (currentOrder.message != null) {
            context.makeToast(currentOrder.message)
        }
    }
    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = R.string.home_screen_title,
                onNavigationIconClicked = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                },
            )
        },
        floatingActionButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (currentTotalPrice != 0.0 && currentTotalPrice != null) {
                    FloatingActionButton(onClick = {
                        isCurrentOrderDialogOpen.value = !isCurrentOrderDialogOpen.value
                    }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.ShoppingBag,
                                contentDescription = stringResource(R.string.current_order_fab_description)
                            )
                            Text(text = stringResource(id = R.string.price_format, currentTotalPrice))
                        }
                    }
                }
                FloatingActionButton(onClick = {
                    isMenuItemAddDialogOpen.value = !isMenuItemAddDialogOpen.value
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_menu_item_to_database_description)
                    )
                }
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            val menuItems = menuItemsDataState.menuItems ?: listOf()

            LazyColumn {
                val groupedItems = groupMenuItemsByType(menuItems)
                groupedItems.forEach { (type, items) ->
                    item {
                        MenuSection(title = type, items = items, orderViewModel = orderViewModel)
                    }
                }
            }


        }
    }
    if (isMenuItemAddDialogOpen.value) {
        MenuItemAddAlertDialog(
            isDialogOpen = isMenuItemAddDialogOpen,
            onDialogConfirmedClicked = {
                menuViewModel.onMenuItemEvent(MenuItemEvent.OnAddMenuItem(it))
            },
        )
    }
    if (isCurrentOrderDialogOpen.value) {
        CurrentOrderAlertDialog(
            isDialogOpen = isCurrentOrderDialogOpen,
            onDialogConfirmClicked = {
                context.makeToast("We added your order to queue.")
                orderViewModel.onOrderEvent(OrderEvent.OnAddOrder)
                isCurrentOrderDialogOpen.value = false
            },
            currentOrder = currentOrder.order,
            onCurrentOrderMenuItemDelete = {
                orderViewModel.onOrderEvent(
                    OrderEvent.OnRemoveMenuItemFromCurrentOrder(it)
                )
            },
            onMenuItemQuantityDecrement = {
                orderViewModel.onOrderEvent(
                    OrderEvent.OnDecrementMenuItemQuantity(it)
                )
            },
            onMenuItemQuantityIncrement = {
                orderViewModel.onOrderEvent(
                    OrderEvent.OnIncrementMenuItemQuantity(it)
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItemCard(menuItem: MenuItem, orderViewModel: OrderViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        onClick = {
            orderViewModel.onOrderEvent(OrderEvent.OnAddMenuItemToCurrentOrder(menuItem))
        }
    ) {
        Box(modifier= Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(75.dp)
                    .alpha(0.3f) ,
                imageVector = getIconForMenuItemTypes(menuItem.menuItemType),
                contentDescription = stringResource(R.string.menu_item_card_description)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(menuItem.itemName)
                Text("Price: ${menuItem.itemPrice}$")
            }
        }
    }
}

@Composable
fun MenuSection(
    title: String,
    items: List<MenuItem>,
    orderViewModel: OrderViewModel,
) {
    Column {
        Text(
            text = title,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
        items.forEach { menuItem ->
            MenuItemCard(menuItem, orderViewModel)
        }

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

