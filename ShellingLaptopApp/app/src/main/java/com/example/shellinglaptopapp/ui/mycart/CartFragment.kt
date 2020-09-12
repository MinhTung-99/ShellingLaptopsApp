package com.example.shellinglaptopapp.ui.mycart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setVisibleToolBar(false)

        val cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel.carts.observe(viewLifecycleOwner, {
            rv_product.adapter = CartAdapter(it)

            val itemDeclaration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            rv_product.addItemDecoration(itemDeclaration)
        })
    }
}