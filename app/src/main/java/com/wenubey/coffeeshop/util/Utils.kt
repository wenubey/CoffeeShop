package com.wenubey.coffeeshop.util

import android.icu.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(calendar.time)
}