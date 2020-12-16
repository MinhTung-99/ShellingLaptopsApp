package com.shelllaptop.fragment.laptop

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.shelllaptop.R
import com.shelllaptop.network.LaptopApi

class LaptopFragment: Fragment(R.layout.fragment_laptop) {

    private val api by lazy {
        LaptopApi()
    }

    private val repository by lazy {
        LaptopRepository(api)
    }

    private val factory by lazy {
        LaptopViewModelFactory(repository)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(LaptopViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getLaptops()
        viewModel.laptops.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.informationLaptop!![0].brand, Toast.LENGTH_SHORT).show()
        })
    }
}