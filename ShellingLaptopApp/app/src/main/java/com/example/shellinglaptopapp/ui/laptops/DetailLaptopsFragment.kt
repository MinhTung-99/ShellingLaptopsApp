package com.example.shellinglaptopapp.ui.laptops

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.data.model.Laptop
import com.example.shellinglaptopapp.data.network.LaptopApi
import com.example.shellinglaptopapp.data.repository.LaptopRepository
import kotlinx.android.synthetic.main.fragment_detail_laptop.*

class DetailLaptopsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_laptop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity()).get(ShareLaptopViewModel::class.java)
        viewModel.laptop.observe(viewLifecycleOwner, {
            Glide.with(view.context)
                .load(it.image)
                .into(image)
            txt_brand.text = it.brand
            txt_name.text = it.name
            txt_cpu.text = it.cpu
            txt_card_graphic.text = it.cardGraphic
            txt_hard_drive.text = it.hardDrive
            txt_ram.text = it.ram
            txt_display.text = it.display
            txt_weight.text = " " + it.weight
            txt_color.text = it.color
            txt_os.text = it.os
            txt_pin.text = it.pin
            txt_price.text = it.priceStr
        })
    }
}