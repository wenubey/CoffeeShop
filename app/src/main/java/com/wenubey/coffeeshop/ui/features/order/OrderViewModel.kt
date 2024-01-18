package com.wenubey.coffeeshop.ui.features.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.data.local.entities.Order
import com.wenubey.coffeeshop.domain.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(
    private val repo: OrderRepository
) : ViewModel() {

    private val _orderDataState = MutableLiveData<OrderDataState>()
    val orderDataState: LiveData<OrderDataState>
        get() = _orderDataState

    private val currentOrderItems = mutableListOf<MenuItem>()

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double>
        get() = _totalPrice


    fun onOrderEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.OnClearOrderHistory -> clearOrderHistory()
            is OrderEvent.OnAddOrder -> addOrder()
            is OrderEvent.OnDeleteOrder -> deleteOrder(event.order)
            is OrderEvent.OnGetOrder -> getOrder(event.id)
            is OrderEvent.OnGetAllOrders -> getAllOrders()
            is OrderEvent.OnAddMenuItem -> addMenuItemToOrder(event.menuItem)
        }
    }

    private fun addMenuItemToOrder(menuItem: MenuItem) {
        currentOrderItems.add(menuItem)

        val total = currentOrderItems.sumOf { it.itemPrice }
        _totalPrice.postValue(total)
    }

    private fun getAllOrders() = viewModelScope.launch {
        val result = repo.getAllOrders()
        if (result.isSuccess) {
            _orderDataState.postValue(OrderDataState(orders = result.getOrNull()))
        } else {
            _orderDataState.postValue(OrderDataState(error = result.exceptionOrNull()?.message))
        }
    }

    private fun getOrder(id: String) = viewModelScope.launch {
        val result = repo.getOrder(id)
        if (result.isSuccess) {
            _orderDataState.postValue(OrderDataState(order = result.getOrNull()))
        } else {
            _orderDataState.postValue(OrderDataState(error = result.exceptionOrNull()?.message))
        }
    }

    private fun deleteOrder(order: Order) = viewModelScope.launch {
        val result = repo.deleteOrder(order)
        if (result.isSuccess) {
            _orderDataState.postValue(OrderDataState(message = result.getOrNull()))
        } else {
            _orderDataState.postValue(OrderDataState(error = result.exceptionOrNull()?.message))
        }
    }


    private fun addOrder()  = viewModelScope.launch {
        val newOrder = Order(
            items = currentOrderItems,
            totalPrice = _totalPrice.value ?: 0.0,
        )
        val result = repo.addOrder(newOrder)
        if (result.isSuccess) {
            _orderDataState.postValue(OrderDataState(message = result.getOrNull()))
        } else {
            _orderDataState.postValue(OrderDataState(error = result.exceptionOrNull()?.message))
        }
    }

    private fun clearOrderHistory() = viewModelScope.launch {
        val result = repo.clearOrderHistory()
        if (result.isSuccess) {
            _orderDataState.postValue(OrderDataState(message = result.getOrNull()))
        } else {
            _orderDataState.postValue(OrderDataState(error = result.exceptionOrNull()?.message))
        }
    }

}