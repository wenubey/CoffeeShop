package com.wenubey.coffeeshop.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.coffeeshop.R
import com.wenubey.coffeeshop.data.local.entities.Order
import com.wenubey.coffeeshop.ui.theme.CoffeeShopTheme
import com.wenubey.coffeeshop.util.fakeOrder

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
