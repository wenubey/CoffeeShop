package com.wenubey.coffeeshop.ui.features.menu_item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.coffeeshop.data.local.entities.MenuItem
import com.wenubey.coffeeshop.domain.MenuItemRepository
import kotlinx.coroutines.launch

class MenuItemViewModel(
    private val repo: MenuItemRepository,
): ViewModel() {

    private val _menuItemDataState = MutableLiveData<MenuItemDataState>()
    val menuItemDataState: LiveData<MenuItemDataState>
        get() = _menuItemDataState


    fun onMenuItemEvent(event: MenuItemEvent) {
        when(event) {
            is MenuItemEvent.OnGetAllMenuItems -> getAllMenuItems()
            is MenuItemEvent.OnGetMenuItem -> getMenuItem(event.name)
            is MenuItemEvent.OnAddMenuItem -> addMenuItem(event.menuItem)
            is MenuItemEvent.OnClearMenuItems -> clearMenuItems()
            is MenuItemEvent.OnDeleteMenuItem -> deleteMenuItem(event.menuItem)
        }
    }

    private fun deleteMenuItem(menuItem: MenuItem) = viewModelScope.launch {
        val result = repo.deleteMenuItem(menuItem)
        if (result.isSuccess) {
            _menuItemDataState.postValue(MenuItemDataState(message = result.getOrNull()))
        } else {
            _menuItemDataState.postValue(MenuItemDataState(error = result.exceptionOrNull()?.message))
        }
    }

    private fun clearMenuItems() = viewModelScope.launch {
        val result = repo.clearMenuItems()
        if (result.isSuccess) {
            _menuItemDataState.postValue(MenuItemDataState(message = result.getOrNull()))
        } else {
            _menuItemDataState.postValue(MenuItemDataState(error = result.exceptionOrNull()?.message))
        }
    }

    private fun addMenuItem(menuItem: MenuItem) = viewModelScope.launch {
        val result = repo.addMenuItem(menuItem)
        if (result.isSuccess) {
            _menuItemDataState.postValue(MenuItemDataState(message = result.getOrNull()))
        } else {
            _menuItemDataState.postValue(MenuItemDataState(error = result.exceptionOrNull()?.message))
        }
    }

    private fun getMenuItem(name: String) = viewModelScope.launch {
        val result = repo.getMenuItem(name)
        if (result.isSuccess) {
            _menuItemDataState.postValue(MenuItemDataState(menuItem = result.getOrNull()))
        } else {
            _menuItemDataState.postValue(MenuItemDataState(error = result.exceptionOrNull()?.message))
        }
    }

    private fun getAllMenuItems() = viewModelScope.launch {
        val result = repo.getAllMenuItems()
        if(result.isSuccess) {
            _menuItemDataState.postValue(MenuItemDataState(menuItems = result.getOrNull()))
        } else {
            _menuItemDataState.postValue(MenuItemDataState(error = result.exceptionOrNull()?.message))
        }
    }
}