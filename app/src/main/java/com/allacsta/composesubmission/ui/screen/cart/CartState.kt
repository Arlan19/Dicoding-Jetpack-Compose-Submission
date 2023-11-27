package com.allacsta.composesubmission.ui.screen.cart

import com.allacsta.composesubmission.model.OrderCoffee

data class CartState(
    val orderCoffee: List<OrderCoffee>,
    val totalRequiredPoint: Int
)