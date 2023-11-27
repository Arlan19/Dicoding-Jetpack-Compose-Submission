package com.allacsta.composesubmission.di

import com.allacsta.composesubmission.data.CoffeeRepository


object Injection {
    fun provideRepository(): CoffeeRepository {
        return CoffeeRepository.getInstance()
    }
}