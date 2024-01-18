package com.wenubey.coffeeshop.di

import androidx.room.Room
import com.wenubey.coffeeshop.data.local.CoffeeShopDatabase
import com.wenubey.coffeeshop.data.repository.OrderRepositoryImpl
import com.wenubey.coffeeshop.data.repository.FeedbackRepositoryImpl
import com.wenubey.coffeeshop.data.repository.MenuItemRepositoryImpl
import com.wenubey.coffeeshop.domain.OrderRepository
import com.wenubey.coffeeshop.domain.FeedbackRepository
import com.wenubey.coffeeshop.domain.MenuItemRepository
import com.wenubey.coffeeshop.ui.features.order.OrderViewModel
import com.wenubey.coffeeshop.ui.features.feedback.FeedbackViewModel
import com.wenubey.coffeeshop.ui.features.menu_item.MenuItemViewModel
import com.wenubey.coffeeshop.util.Constants.DB_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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
    factory { get<CoffeeShopDatabase>().feedbackDao }
}

val repositoryModules = module {
    single<OrderRepository> { OrderRepositoryImpl(get()) }
    single<MenuItemRepository> { MenuItemRepositoryImpl(get()) }
    single<FeedbackRepository> { FeedbackRepositoryImpl(get()) }
}

val viewModelModules = module {
    viewModel { OrderViewModel(get()) }
    viewModel { MenuItemViewModel(get()) }
    viewModel { FeedbackViewModel(get()) }
}
