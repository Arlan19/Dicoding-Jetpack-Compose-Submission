package com.allacsta.composesubmission.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allacsta.composesubmission.data.CoffeeRepository
import com.allacsta.composesubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CoffeeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderCoffees() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderCoffees()
                .collect { orderCoffee ->
                    val totalRequiredPoint =
                        orderCoffee.sumOf { it.coffee.requiredPoint * it.count }
                    _uiState.value = UiState.Success(CartState(orderCoffee, totalRequiredPoint))
                }
        }
    }

    fun updateOrderCoffee(coffeeId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderCoffee(coffeeId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderCoffees()
                    }
                }
        }
    }
}