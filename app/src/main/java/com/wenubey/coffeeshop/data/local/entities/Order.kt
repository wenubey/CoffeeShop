package com.wenubey.coffeeshop.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wenubey.coffeeshop.util.Constants.ORDER_TABLE_NAME
import com.wenubey.coffeeshop.util.getCurrentTime
import java.util.UUID

@Entity(tableName = ORDER_TABLE_NAME)
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "orderId")
    val orderId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "items")
    val items: MutableList<MenuItem>,
    @ColumnInfo(name = "totalPrice")
    var totalPrice: Double,
    @ColumnInfo(name = "orderedAt")
    val orderedAt: String = getCurrentTime(),
)