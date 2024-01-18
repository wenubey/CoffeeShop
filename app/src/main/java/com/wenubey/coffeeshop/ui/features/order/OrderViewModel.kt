package com.wenubey.coffeeshop.ui.features.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wenubey.coffeeshop.data.local.entities.Order
import com.wenubey.coffeeshop.domain.OrderRepository

class OrderViewModel(
    private val repo: OrderRepository
) : ViewModel() {

    private val _orderDataState = MutableLiveData<OrderDataState>()
    val orderDataState: LiveData<OrderDataState>
        get() = _orderDataState


    fun onEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.OnClearOrderHistory -> clearOrderHistory()
            is OrderEvent.OnAddOrder -> addOrder(event.order)
            is OrderEvent.OnDeleteOrder -> deleteOrder(event.order)
            is OrderEvent.OnGetOrder -> getOrder(event.id)
            is OrderEvent.OnGetAllOrders -> getAllOrders()
        }
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