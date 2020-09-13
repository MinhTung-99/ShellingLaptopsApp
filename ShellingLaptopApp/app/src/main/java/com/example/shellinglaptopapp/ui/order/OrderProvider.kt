package com.example.shellinglaptopapp.ui.order

import com.example.shellinglaptopapp.data.model.Cart

interface OrderProvider {
    fun fullName(): String
    fun phoneNumber(): String
    fun email(): String
    fun address(): String
    fun showMessage()
    fun deleteCart(cart: Cart)
}