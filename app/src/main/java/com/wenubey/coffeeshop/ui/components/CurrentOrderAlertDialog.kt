package com.wenubey.coffeeshop.ui.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme
import com.wenubey.coffeeshop.util.fakeMenuItem

@Composable
fun CurrentOrderAlertDialog(
    isDialogOpen: MutableState<Boolean>,
    onDialogConfirmClicked: () -> Unit,
    currentOrder: Order?,
    onMenuItemQuantityIncrement: (menuItem: MenuItem) -> Unit,
    onCurrentOrderMenuItemDelete: (menuItem: MenuItem) -> Unit,
    onMenuItemQuantityDecrement: (menuItem: MenuItem) -> Unit,
) {
    CurrentOrderAlertDialogContent(
        isDialogOpen = isDialogOpen,
        onDialogConfirmClicked = onDialogConfirmClicked,
        currentOrder = currentOrder,
        onMenuItemQuantityIncrement = onMenuItemQuantityIncrement,
        onMenuItemQuantityDecrement = onMenuItemQuantityDecrement,
        onCurrentOrderMenuItemDelete = onCurrentOrderMenuItemDelete
    )
}

@Composable
private fun CurrentOrderAlertDialogContent(
    isDialogOpen: MutableState<Boolean> = mutableStateOf(false),
    onDialogConfirmClicked: () -> Unit = {},
    currentOrder: Order? = Order(items = mutableListOf(fakeMenuItem), totalPrice = 0.0),
    onMenuItemQuantityIncrement: (menuItem: MenuItem) -> Unit = {},
    onCurrentOrderMenuItemDelete: (menuItem: MenuItem) -> Unit = {},
    onMenuItemQuantityDecrement: (menuItem: MenuItem) -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = { isDialogOpen.value = false },
        confirmButton = {
            Column {
                Text(text = "Current Total: ${currentOrder?.totalPrice}$")
                Button(
                    onClick = onDialogConfirmClicked,
                ) {
                    Text(text = "Pay now")
                }
            }

        },
        title = {
            Text(text = stringResource(R.string.current_order_title))
        },
        text = {
            if (!currentOrder?.items.isNullOrEmpty()) {
                LazyColumn {
                    items(currentOrder?.items!!.toList()) {
                        CurrentOrderCard(
                            menuItem = it,
                            onMenuItemQuantityIncrement = onMenuItemQuantityIncrement,
                            onCurrentOrderMenuItemDelete = onCurrentOrderMenuItemDelete,
                            onMenuItemQuantityDecrement = onMenuItemQuantityDecrement,
                        )
                    }
                }
            } else {
                Text(text = stringResource(R.string.card_empty_message))
            }
        }
    )
}

@Composable
private fun CurrentOrderCard(
    menuItem: MenuItem = fakeMenuItem,
    onMenuItemQuantityIncrement: (menuItem: MenuItem) -> Unit = {},
    onCurrentOrderMenuItemDelete: (menuItem: MenuItem) -> Unit = {},
    onMenuItemQuantityDecrement: (menuItem: MenuItem) -> Unit = {},

    ) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = menuItem.itemName)
                Text(text = "Quantity: ${menuItem.itemQuantity}")
                Text(text = "Price: ${menuItem.itemPrice}$")
                Text(text = "Subtotal: ${menuItem.itemPrice * menuItem.itemQuantity}$")
            }
            Row {
                Icon(
                    modifier = Modifier.clickable(
                        MutableInteractionSource(),
                        onClick = {
                            onMenuItemQuantityIncrement(menuItem)
                        },
                        indication = null,
                    ),
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.increment_item_quantity_description)
                )

                Icon(
                    modifier = Modifier.clickable(
                        MutableInteractionSource(),
                        onClick = {
                            onCurrentOrderMenuItemDelete(menuItem)
                        },
                        indication = null,
                    ),
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete_current_order_item_description)
                )

                Icon(
                    modifier = Modifier.clickable(
                        MutableInteractionSource(),
                        onClick = {
                            onMenuItemQuantityDecrement(menuItem)
                        },
                        indication = null,
                    ),
                    imageVector = Icons.Default.Remove,
                    contentDescription = stringResource(R.string.decrement_item_quantity_description)
                )
            }
        }

    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun CurrentMenuItemCardPreview() {
    CoffeeShopTheme {
        Surface {
            CurrentOrderCard()
        }
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun CurrentOrderAlertDialogContentPreview() {
    CoffeeShopTheme {
        Surface {
            CurrentOrderAlertDialogContent()
        }
    }
}