package com.example.shellinglaptopapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.adapter.LaptopAdapter
import com.example.shellinglaptopapp.api.LaptopApi
import com.example.shellinglaptopapp.api.LaptopRepository
import com.example.shellinglaptopapp.viewmodel.LaptopViewModel
import com.example.shellinglaptopapp.viewmodel.LaptopViewModelFactory
import kotlinx.android.synthetic.main.fragment_laptop.*

class LaptopFragment: Fragment() {

    private lateinit var factory: LaptopViewModelFactory
    private lateinit var viewModel: LaptopViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_laptop, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = LaptopApi()
        val repository = LaptopRepository(api)
        factory = LaptopViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(LaptopViewModel::class.java)
        viewModel.getLaptops()
        viewModel.laptops.observe(viewLifecycleOwner, Observer {
            rv_laptop.layoutManager = GridLayoutManager(context, 2)
            rv_laptop.adapter = LaptopAdapter(it.informationLaptop!!)
        })
    }
}