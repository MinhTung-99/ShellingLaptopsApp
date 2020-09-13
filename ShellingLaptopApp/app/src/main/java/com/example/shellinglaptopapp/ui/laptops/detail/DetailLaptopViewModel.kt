package com.example.shellinglaptopapp.ui.laptops.detail

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.data.model.Laptop
import com.example.shellinglaptopapp.ui.cart.CartFragment
import com.example.shellinglaptopapp.ui.cart.CartViewModel

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
           laptop.price!!,
           laptop.priceStr!!,
           _count,
           laptop.price!! * _count,
           ""
        ))

        val fragmentTransaction =
            (view.context as AppCompatActivity).supportFragmentManager?.beginTransaction()
        val cartFragment = CartFragment()
        fragmentTransaction.replace(R.id.fragment, cartFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
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