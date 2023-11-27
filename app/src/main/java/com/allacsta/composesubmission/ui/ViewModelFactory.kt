package com.allacsta.composesubmission.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allacsta.composesubmission.data.CoffeeRepository
import com.allacsta.composesubmission.ui.screen.cart.CartViewModel
import com.allacsta.composesubmission.ui.screen.detail.DetailCoffeeViewModel
import com.allacsta.composesubmission.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: CoffeeRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailCoffeeViewModel::class.java)) {
            return DetailCoffeeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}