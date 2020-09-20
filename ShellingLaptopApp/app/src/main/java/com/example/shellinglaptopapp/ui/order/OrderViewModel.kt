package com.example.shellinglaptopapp.ui.order

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.data.model.Pay
import com.example.shellinglaptopapp.data.repository.LaptopRepository
import com.example.shellinglaptopapp.util.Coroutines
import kotlinx.coroutines.Job

class OrderViewModel(
    private val repository: LaptopRepository,
): ViewModel() {

    private lateinit var job: Job

    lateinit var cart: Cart
    lateinit var orderProvider: OrderProvider

    private fun postPay(pay: Pay){
        job = Coroutines.ioThenMain(
            {repository.postPays(pay)},
            {}
        )
    }

    override fun onCleared() {
        super.onCleared()

        if(::job.isInitialized) job.cancel()
    }

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

        postPay(pay)
        orderProvider.showMessage()
        orderProvider.deleteCart(cart)
    }
}