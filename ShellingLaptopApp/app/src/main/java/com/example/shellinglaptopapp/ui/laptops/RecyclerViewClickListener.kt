package com.example.shellinglaptopapp.ui.laptops

import com.example.shellinglaptopapp.data.model.Laptop

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(laptop: Laptop)
}