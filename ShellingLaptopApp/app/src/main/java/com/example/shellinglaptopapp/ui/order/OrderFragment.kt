package com.example.shellinglaptopapp.ui.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.data.model.Pay
import com.example.shellinglaptopapp.data.network.LaptopApi
import com.example.shellinglaptopapp.data.repository.LaptopRepository
import com.example.shellinglaptopapp.databinding.FragmentOrderBinding
import com.example.shellinglaptopapp.ui.cart.CartViewModel
import com.example.shellinglaptopapp.ui.laptops.LaptopViewModelFactory
import com.example.shellinglaptopapp.ui.share.ShareCartViewModel
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment: Fragment(), OrderProvider {

    private lateinit var orderViewMode: OrderViewModel
    private lateinit var binding: FragmentOrderBinding

    private lateinit var factory: PayViewModelFactory
    private lateinit var payViewModel: PayViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_order,
            container,
            false
        )
        orderViewMode = ViewModelProvider(this).get(OrderViewModel::class.java)
        binding.order = orderViewMode

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val api = LaptopApi()
        val repository = LaptopRepository(api)
        factory = PayViewModelFactory(repository)
        payViewModel = ViewModelProvider(this, factory).get(PayViewModel::class.java)
        orderViewMode.payViewModel = payViewModel
        orderViewMode.orderProvider = this

        val viewModel = ViewModelProvider(requireActivity()).get(ShareCartViewModel::class.java)
        viewModel.cart.observe(viewLifecycleOwner, {
            Log.e("KMFFFF", it.name!!)
            orderViewMode.cart = it
        })
    }

    override fun fullName(): String {
        return edt_full_name.text.toString()
    }

    override fun phoneNumber(): String {
        return  edt_phone_number.text.toString()
    }

    override fun email(): String {
        return edt_email.text.toString()
    }

    override fun address(): String {
        return edt_address.text.toString()
    }

    override fun showMessage() {
        btn_order.isEnabled = false
        Toast.makeText(context, "Đã đặt hàng", Toast.LENGTH_SHORT).show()
    }

    override fun deleteCart(cart: Cart) {
        val viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        viewModel.deleteCart(cart)
    }

}