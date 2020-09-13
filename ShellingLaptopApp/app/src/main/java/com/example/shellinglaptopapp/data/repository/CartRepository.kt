package com.example.shellinglaptopapp.data.repository

import androidx.lifecycle.LiveData
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.data.offline.CartDao

class CartRepository(
    private val cartDao: CartDao
) {
    val carts: LiveData<List<Cart>> = cartDao.getAllCarts()

    suspend fun insertCart(cart: Cart){
        cartDao.insert(cart)
    }

    suspend fun deleteCart(cart: Cart){
        cartDao.delete(cart)
    }
}