package com.allacsta.composesubmission.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allacsta.composesubmission.data.CoffeeRepository
import com.allacsta.composesubmission.model.OrderCoffee
import com.allacsta.composesubmission.model.Coffee
import com.allacsta.composesubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailCoffeeViewModel(
    private val repository: CoffeeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderCoffee>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderCoffee>>
        get() = _uiState

    fun getCoffeeById(coffeeId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderCoffeeById(coffeeId))
        }
    }

    fun addToCart(coffee: Coffee, count: Int) {
        viewModelScope.launch {
            repository.updateOrderCoffee(coffee.id, count)
        }
    }
}