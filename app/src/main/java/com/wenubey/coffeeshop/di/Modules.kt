package com.wenubey.coffeeshop.di

import androidx.room.Room
import com.wenubey.coffeeshop.data.local.CoffeeShopDatabase
import com.wenubey.coffeeshop.util.Constants.DB_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            CoffeeShopDatabase::class.java,
            DB_NAME
        ).build()
    }
    factory { get<CoffeeShopDatabase>().menuItemDao }
    factory { get<CoffeeShopDatabase>().orderDao }
}
