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

    private val _currentOrderItems = mutableListOf<MenuItem>()

    private val _currentOrder = MutableLiveData(Order(items = mutableListOf(), totalPrice = 0.0))


    fun onOrderEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.OnClearOrderHistory -> clearOrderHistory()
            is OrderEvent.OnAddOrder -> addOrder()
            is OrderEvent.OnDeleteOrder -> deleteOrder(event.order)
            is OrderEvent.OnGetOrder -> getOrder(event.id)
            is OrderEvent.OnGetAllOrders -> getAllOrders()
            is OrderEvent.OnAddMenuItemToCurrentOrder -> addMenuItemToOrder(event.menuItem)
            is OrderEvent.OnRemoveMenuItemFromCurrentOrder -> removeMenuItemFromOrder(event.menuItem)
            is OrderEvent.OnIncrementMenuItemQuantity -> incrementMenuItemQuantity(event.menuItem)
            is OrderEvent.OnDecrementMenuItemQuantity -> decrementMenuItemQuantity(event.menuItem)

        }
    }

    private fun updateOrder() {
        val total = _currentOrderItems.sumOf { it.itemPrice * it.itemQuantity }
        _currentOrder.value = _currentOrder.value?.copy(totalPrice = total, items = _currentOrderItems)
        _orderDataState.postValue(OrderDataState(order = _currentOrder.value))
    }

    private fun resetOrder() {
        _currentOrderItems.clear()
        _currentOrder.value = Order(items = mutableListOf(), totalPrice = 0.0)
        updateOrder()
    }

    private fun addMenuItemToOrder(menuItem: MenuItem) {
        val existingItem = _currentOrderItems.find { it.itemId == menuItem.itemId }
        if (existingItem != null) {
            existingItem.itemQuantity++
        } else {
            _currentOrderItems.add(menuItem.copy(itemQuantity = 1))
        }
        updateOrder()
    }

    private fun removeMenuItemFromOrder(menuItem: MenuItem) {
        _currentOrderItems.removeAll { it.itemId == menuItem.itemId }
        updateOrder()
    }

    private fun incrementMenuItemQuantity(menuItem: MenuItem) {
        val existingItem = _currentOrderItems.find { it.itemId == menuItem.itemId }
        existingItem?.let {
            it.itemQuantity++
            updateOrder()
        }
    }

    private fun decrementMenuItemQuantity(menuItem: MenuItem) {
        val existingItem = _currentOrderItems.find { it.itemId == menuItem.itemId }
        if (existingItem != null && existingItem.itemQuantity > 1) {
            existingItem.itemQuantity--
            updateOrder()
        }
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
        resetOrder()
    }


    private fun addOrder()  = viewModelScope.launch {
        val result = repo.addOrder(_currentOrder.value!!)
        resetOrder()
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