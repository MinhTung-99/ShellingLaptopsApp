package com.example.shellinglaptopapp.ui.mycart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.data.offline.CartRoomDatabase
import com.example.shellinglaptopapp.data.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val carts: LiveData<List<Cart>>
    private val repository: CartRepository

    init {
        val cartDao = CartRoomDatabase.getDatabase(application).cartDao()
        repository = CartRepository(cartDao)
        carts = repository.carts
    }

    fun insertCart(cart: Cart){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCart(cart)
        }
    }
}