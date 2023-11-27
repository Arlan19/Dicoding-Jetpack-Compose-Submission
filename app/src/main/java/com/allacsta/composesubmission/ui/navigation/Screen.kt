package com.allacsta.composesubmission.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailCoffee : Screen("home/{coffeeId}") {
        fun createRoute(coffeeId: Long) = "home/$coffeeId"
    }
}
