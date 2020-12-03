package com.example.shellinglaptopapp.ui.laptops.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.databinding.FragmentDetailLaptopBinding
import com.example.shellinglaptopapp.ui.MainActivity
import com.example.shellinglaptopapp.ui.share.ShareLaptopViewModel
import com.example.shellinglaptopapp.ui.cart.CartViewModel
import kotlinx.android.synthetic.main.fragment_detail_laptop.*

class DetailLaptopsFragment: Fragment() {

    private val detailLaptopViewModel by lazy {
        ViewModelProvider(this).get(DetailLaptopViewModel::class.java)
    }

    private val shareLaptopViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ShareLaptopViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailLaptopBinding.inflate(inflater, container, false).also {
        it.lifecycleOwner = this
        it.viewModel = detailLaptopViewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shareLaptopViewModel.laptop.observe(viewLifecycleOwner, {
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

            detailLaptopViewModel.laptop = it
        })

        detailLaptopViewModel.cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        detailLaptopViewModel.count.observe(viewLifecycleOwner, {
            txt_count.text = it.toString()
        })
    }
}