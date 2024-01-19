package com.wenubey.coffeeshop.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme

@Composable
fun MenuItemAddAlertDialog(
    onDialogConfirmedClicked: (menuItem: MenuItem) -> Unit,
    isDialogOpen: MutableState<Boolean>,
) {
    MenuItemAddAlertDialogContent(
        isDialogOpen = isDialogOpen,
        onDialogConfirmedClicked = onDialogConfirmedClicked,
    )
}

@Composable
private fun MenuItemAddAlertDialogContent(
    isDialogOpen: MutableState<Boolean> = mutableStateOf(false),
    onDialogConfirmedClicked: (menuItem: MenuItem) -> Unit = {},
) {
    var menuItemName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var menuItemPrice by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var selectedMenuItemType by remember {
        mutableStateOf(MenuItemType.DRINKS)
    }

    AlertDialog(
        onDismissRequest = { isDialogOpen.value = false },
        confirmButton = {
            Button(
                onClick = {
                    onDialogConfirmedClicked(
                        MenuItem(
                            itemName = menuItemName.text,
                            menuItemType = selectedMenuItemType,
                            itemPrice = menuItemPrice.text.toDouble(),
                        )
                    )
                    isDialogOpen.value = false
                },
            ) {
                Text(text = "Add")
            }
        },
        title = {
            Text(text = stringResource(R.string.add_menu_item))
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                OutlinedTextField(
                    value = menuItemName,
                    onValueChange = { menuItemName = it },
                    placeholder = { Text(stringResource(R.string.menu_item_name_placeholder)) }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = stringResource(R.string.menu_item_type),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Divider(thickness = 1.dp)
                    MenuItemType.entries.forEach { menuItemType ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedMenuItemType == menuItemType,
                                onClick = {
                                    selectedMenuItemType = menuItemType
                                },
                            )
                            Text(text = menuItemType.name)
                        }

                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = menuItemPrice,
                    onValueChange = { menuItemPrice = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = { Text(stringResource(R.string.menu_item_price_placeholder)) }
                )
            }
        }
    )
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun MenuItemAddAlertDialogContentPreview() {
    CoffeeShopTheme {
        Surface {
            MenuItemAddAlertDialogContent()
        }
    }
}

