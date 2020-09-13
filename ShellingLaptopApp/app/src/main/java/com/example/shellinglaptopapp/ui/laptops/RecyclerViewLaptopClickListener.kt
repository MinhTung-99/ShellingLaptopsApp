package com.example.shellinglaptopapp.ui.laptops

import com.example.shellinglaptopapp.data.model.Laptop

interface RecyclerViewLaptopClickListener {
    fun onRecyclerViewItemClick(laptop: Laptop)
}