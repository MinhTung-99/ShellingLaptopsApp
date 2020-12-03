package com.example.shellinglaptopapp.ui.laptops.detail

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.data.model.Laptop
import com.example.shellinglaptopapp.ui.cart.CartFragment
import com.example.shellinglaptopapp.ui.cart.CartViewModel

class DetailLaptopViewModel: ViewModel() {

    lateinit var cartViewModel: CartViewModel
    lateinit var laptop: Laptop
    lateinit var context: Context
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

        if(_count == 0){
            Toast.makeText(context, "Số lượng đặt mua phải lớn hơn 0", Toast.LENGTH_SHORT).show()
        }else{
//            val fragmentTransaction =
//                (view.context as AppCompatActivity).supportFragmentManager?.beginTransaction()
//            val cartFragment = CartFragment()
//            fragmentTransaction.replace(R.id.fragment, cartFragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()

            view.findNavController().navigate(R.id.action_detailLaptopsFragment_to_cartFragment)
        }
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