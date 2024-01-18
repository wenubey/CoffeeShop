package com.wenubey.coffeeshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wenubey.coffeeshop.data.local.entities.Feedback
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order
import com.wenubey.coffeeshop.domain.OrderRepository
import com.wenubey.coffeeshop.ui.features.menu_item.MenuItemsDataState

class CoffeeShopViewModel(
    private val orderRepository: OrderRepository
) : ViewModel() {



    private val _orderDataState = MutableLiveData<OrderDataState>()
    val orderDataState: LiveData<OrderDataState>
        get() = _orderDataState

    private val _feedbackDataState = MutableLiveData<FeedbackDataState>()
    val feedbackDataState: LiveData<FeedbackDataState>
        get() = _feedbackDataState


    fun onEvent(event: CoffeeShopEvent) {
        when (event) {
            is CoffeeShopEvent.OnClearOrderHistory -> clearOrderHistory()
            is CoffeeShopEvent.OnAddOrder -> addOrder(event.order)
            is CoffeeShopEvent.OnDeleteOrder -> deleteOrder(event.order)
            is CoffeeShopEvent.OnGetOrder -> getOrder(event.id)
            is CoffeeShopEvent.OnGetAllOrders -> getAllOrders()
            is CoffeeShopEvent.OnClearFeedbacks -> clearFeedbacks()
            is CoffeeShopEvent.OnAddFeedback -> addFeedback(event.feedback)
            is CoffeeShopEvent.OnGetAllFeedbacks -> getAllFeedbacks()
        }
    }

    private fun getAllFeedbacks() {
        TODO("Not yet implemented")
    }

    private fun addFeedback(feedback: Feedback) {
        TODO("Not yet implemented")
    }

    private fun clearFeedbacks() {
        TODO("Not yet implemented")
    }

    private fun getAllOrders() {
        TODO("Not yet implemented")
    }

    private fun getOrder(id: String) {
        TODO("Not yet implemented")
    }

    private fun deleteOrder(order: Order) {
        TODO("Not yet implemented")
    }


    private fun addOrder(order: Order) {
        TODO("Not yet implemented")
    }

    private fun clearOrderHistory() {
        TODO("Not yet implemented")
    }




}