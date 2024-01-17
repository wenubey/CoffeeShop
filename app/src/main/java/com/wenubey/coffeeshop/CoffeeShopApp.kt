package com.wenubey.coffeeshop

import android.app.Application
import com.wenubey.coffeeshop.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoffeeShopApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CoffeeShopApp)
            modules(
                databaseModule,
            )
        }
    }
}