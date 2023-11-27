package com.allacsta.composesubmission.data

import com.allacsta.composesubmission.model.CoffeeDataSource
import com.allacsta.composesubmission.model.OrderCoffee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class CoffeeRepository {

    private val orderCoffees = mutableListOf<OrderCoffee>()

    init {
        if (orderCoffees.isEmpty()) {
            CoffeeDataSource.dummyCoffees.forEach {
                orderCoffees.add(OrderCoffee(it, 0))
            }
        }
    }

    fun getAllCoffee(): Flow<List<OrderCoffee>> {
        return flowOf(orderCoffees)
    }

    fun searchCoffee(query: String): Flow<List<OrderCoffee>>{
        return flowOf(
            orderCoffees.filter {
                it.coffee.title.contains(query, ignoreCase = true)
            }
        )
    }

    fun getOrderCoffeeById(coffeeId: Long): OrderCoffee {
        return orderCoffees.first {
            it.coffee.id == coffeeId
        }
    }

    fun updateOrderCoffee(coffeeId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderCoffees.indexOfFirst { it.coffee.id == coffeeId }
        val result = if (index >= 0) {
            val orderCoffee = orderCoffees[index]
            orderCoffees[index] =
                orderCoffee.copy(coffee = orderCoffee.coffee, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderCoffees(): Flow<List<OrderCoffee>> {
        return getAllCoffee()
            .map { orderCoffees ->
                orderCoffees.filter { orderCoffee ->
                    orderCoffee.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: CoffeeRepository? = null

        fun getInstance(): CoffeeRepository =
            instance ?: synchronized(this) {
                CoffeeRepository().apply {
                    instance = this
                }
            }
    }
}