package com.example.shellinglaptopapp.ui

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.ui.mycart.CartFragment


class MainViewModel: ViewModel() {

    fun moveMyCartOnClick(view: View){
        val fragmentTransaction =
            (view.context as AppCompatActivity).supportFragmentManager?.beginTransaction()
        val cartFragment = CartFragment()
        fragmentTransaction.replace(R.id.fragment, cartFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}