package com.wenubey.coffeeshop.util

import android.content.Context
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.data.local.entities.FeedbackOpinion
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.MenuItemType
import com.wenubey.coffeeshop.data.local.entities.Order
import java.text.SimpleDateFormat
import java.util.Locale

fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(calendar.time)
}

fun Context.makeToast(message: String?) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

val fakeMenuItem = MenuItem(
    itemName = "Item Name",
    menuItemType = MenuItemType.DESSERTS,
    itemPrice = 0.0,
)

val fakeFeedback = Feedback(
    feedBackContent = "Lorem Ipsum is simply dummy text of the printing and typesetting " +
            "industry. Lorem Ipsum has been the industry's standard dummy text ever since the" +
            " 1500s, when an unknown printer took a galley of type and scrambled it to make a " +
            "type specimen book. It has survived not only five centuries, but also the leap into " +
            "electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s " +
            "with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with " +
            "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    feedbackOpinion = FeedbackOpinion.NEGATIVE
)

val fakeOrder = Order(
    items = mutableListOf(
        fakeMenuItem,
        fakeMenuItem,
        fakeMenuItem,
    ),
    totalPrice = 10.0
)

fun groupMenuItemsByType(menuItems: List<MenuItem>): Map<String, List<MenuItem>> {
    return menuItems.groupBy { it.menuItemType.name }
}

@Composable
fun getIconForMenuItemTypes(menuItemType: MenuItemType): ImageVector {
    return when (menuItemType) {
        MenuItemType.DESSERTS -> Icons.Default.Cake
        MenuItemType.DRINKS -> Icons.Default.Coffee
        MenuItemType.SNACKS -> Icons.Default.Fastfood
    }
}

@Composable
fun getIconForFeedbackOpinion(feedbackOpinion: FeedbackOpinion): ImageVector {
    return when(feedbackOpinion) {
        FeedbackOpinion.NEGATIVE -> Icons.Default.ThumbDown
        FeedbackOpinion.POSITIVE -> Icons.Default.ThumbUp
    }
}

@Composable
fun getTintForFeedbackOpinion(feedbackOpinion: FeedbackOpinion): Color {
    return when(feedbackOpinion) {
        FeedbackOpinion.POSITIVE -> Color.Green
        FeedbackOpinion.NEGATIVE -> Color.Red
    }
}