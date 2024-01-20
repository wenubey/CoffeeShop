package com.wenubey.coffeeshop.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.ui.components.CommonTopAppBar
import com.wenubey.coffeeshop.ui.components.ErrorScreen
import com.wenubey.coffeeshop.ui.components.OrderCard
import com.wenubey.coffeeshop.ui.components.SwipeDeleteCard
import com.wenubey.coffeeshop.ui.features.order.OrderDataState
import com.wenubey.coffeeshop.ui.features.order.OrderEvent
import com.wenubey.coffeeshop.ui.features.order.OrderViewModel
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme
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
        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(orders) { order ->
                    SwipeDeleteCard(
                        onSwiped = { viewModel.onOrderEvent(OrderEvent.OnDeleteOrder(order)) },
                        dismissContent = {
                            OrderCard(order = order)
                        },
                    )
                }
            }
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