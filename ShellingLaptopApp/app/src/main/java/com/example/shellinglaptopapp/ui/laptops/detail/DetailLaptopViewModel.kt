package com.example.shellinglaptopapp.ui.laptops.detail

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.data.model.Laptop
import com.example.shellinglaptopapp.ui.mycart.CartViewModel

class DetailLaptopViewModel: ViewModel() {

    lateinit var cartViewModel: CartViewModel
    lateinit var laptop: Laptop
    var count = MutableLiveData<Int>()
    private var _count = 0

    fun btnAddCartOnClick(view: View){

       cartViewModel.insertCart(Cart(
           laptop.image!!,
           laptop.name!!,
           laptop.hardDrive!!,
           laptop.ram!!,
           laptop.priceStr!!,
           _count,
           laptop.price!! * _count,
           ""
       ))
    }

    fun txtIncreaseOnClick(view: View){
        if(_count < 9)
            _count++
        count.value = _count
    }

    fun txtReductionOnClick(view: View){

        if(_count > 0)
            _count--
        count.value = _count
    }

}