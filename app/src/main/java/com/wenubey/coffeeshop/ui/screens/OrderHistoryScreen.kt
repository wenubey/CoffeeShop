package com.wenubey.coffeeshop.ui.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.data.local.entities.Order
import com.wenubey.coffeeshop.ui.components.CommonTopAppBar
import com.wenubey.coffeeshop.ui.components.ErrorScreen
import com.wenubey.coffeeshop.ui.features.order.OrderDataState
import com.wenubey.coffeeshop.ui.features.order.OrderEvent
import com.wenubey.coffeeshop.ui.features.order.OrderViewModel
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme
import com.wenubey.coffeeshop.util.fakeOrder
import com.wenubey.coffeeshop.util.makeToast
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderHistoryScreen(drawerState: DrawerState) {
    OrderHistoryScreenContent(drawerState = drawerState)
}

@Composable
private fun OrderHistoryScreenContent(
    drawerState: DrawerState = DrawerState(initialValue = DrawerValue.Closed),
    viewModel: OrderViewModel = koinViewModel(),
) {
    val scope = rememberCoroutineScope()
    val ordersDataState by viewModel.orderDataState.observeAsState(initial = OrderDataState())
    val orders = ordersDataState.orders

    val context = LocalContext.current
    LaunchedEffect(ordersDataState) {
        viewModel.getAllOrders()
    }
    LaunchedEffect(ordersDataState.message) {
        if (ordersDataState.message != null) {
            context.makeToast(ordersDataState.message)
        }
    }
    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = R.string.order_history_title,
                onNavigationIconClicked = {
                    scope.launch {
                        drawerState.open()
                    }
                },
            )
        }
    ) { paddingValues ->
        if (orders.isNullOrEmpty()) {
            ErrorScreen(error = stringResource(id = R.string.order_history_list_empty_message))
        } else{
            Log.i("XD", "orders: $orders")
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(orders) { order ->
                    DismissCard(order = order, viewModel = viewModel)
                }
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissCard(
    order: Order,
    viewModel: OrderViewModel,
) {
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                viewModel.onOrderEvent(OrderEvent.OnDeleteOrder(order))
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
        dismissContent = {
            OrderCard(
                order = order,
            )
        },
        directions = setOf(DismissDirection.EndToStart),
    )
}


@Composable
fun OrderCard(order: Order = fakeOrder) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .size(100.dp)
                    .alpha(0.05f),
                imageVector = Icons.Default.ShoppingBag,
                contentDescription = stringResource(R.string.shopping_bag_description),
            )
            Column(
                modifier = Modifier.padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = order.orderedAt, style = MaterialTheme.typography.titleLarge)
                Divider(thickness = 2.dp)
                Text(text = "Your Orders", style = MaterialTheme.typography.titleMedium)
                Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth(0.5f))
                order.items.forEach {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = it.itemName)
                        Text(text = stringResource(id = R.string.quantity, it.itemQuantity))
                    }

                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.total_message, order.totalPrice)
                    )
                }
            }

        }
    }
}


@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun OrderCardPreview() {
    CoffeeShopTheme {
        Surface {
            OrderCard()
        }
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun OrderHistoryContentPreview() {
    CoffeeShopTheme {
        Surface {
            OrderHistoryScreenContent()
        }
    }
}