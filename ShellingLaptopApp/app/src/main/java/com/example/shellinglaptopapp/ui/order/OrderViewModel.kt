package com.example.shellinglaptopapp.ui.order

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.data.model.Pay

class OrderViewModel: ViewModel() {

    lateinit var payViewModel: PayViewModel
    lateinit var cart: Cart
    lateinit var orderProvider: OrderProvider

    fun orderOnClick(view: View){

        val pay = Pay(
            cart.image!!,
            cart.name!!,
            cart.hardDrive!!,
            cart.ram!!,
            cart.price!!,
            cart.amount!!,
            cart.totalMoney!!,
            orderProvider.fullName(),
            orderProvider.phoneNumber(),
            orderProvider.email(),
            orderProvider.address()
        )
        payViewModel.postPay(pay)
        orderProvider.showMessage()
        orderProvider.deleteCart(cart)
    }

}