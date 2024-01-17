package com.wenubey.coffeeshop.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wenubey.coffeeshop.data.local.entities.MenuItem

class CoffeeShopTypeConverter {

    private inline fun <reified T> fromJson(json: String?, typeToken: TypeToken<out T>): T? {
        return if (json == null) null else Gson().fromJson(json, typeToken.type)
    }

    private fun <T> toJson(data: T?): String? {
        return data?.let { Gson().toJson(it) }
    }


    @TypeConverter
    fun itemsFromJson(json: String?): List<MenuItem>? =
        fromJson(json, object : TypeToken<List<MenuItem>>() {})

    @TypeConverter
    fun itemsToJson(items: List<MenuItem>?): String? =
        toJson(items)
}