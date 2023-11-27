package com.allacsta.composesubmission.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allacsta.composesubmission.data.CoffeeRepository
import com.allacsta.composesubmission.model.OrderCoffee
import com.allacsta.composesubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CoffeeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderCoffee>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderCoffee>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllCoffees() {
        viewModelScope.launch {
            repository.getAllCoffee()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderCoffees ->
                    _uiState.value = UiState.Success(orderCoffees)
                }
        }
    }

    fun search(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            repository.searchCoffee(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {searchCoffe ->
                    _uiState.value = UiState.Success(searchCoffe)
                }
        }
    }
}