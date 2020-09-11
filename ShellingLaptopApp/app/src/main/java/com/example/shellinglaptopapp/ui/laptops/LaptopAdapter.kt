package com.example.shellinglaptopapp.ui.laptops

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.databinding.ItemLaptopBinding
import com.example.shellinglaptopapp.data.model.Laptop

class LaptopAdapter(
    private val laptops: List<Laptop>,
    private val listener: RecyclerViewClickListener
): RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>() {

    class LaptopViewHolder(
        val itemLaptopBinding: ItemLaptopBinding
    ) : RecyclerView.ViewHolder(itemLaptopBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopViewHolder =
        LaptopViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_laptop,
                parent, false)
        )

    override fun onBindViewHolder(holder: LaptopViewHolder, position: Int) {
        holder.itemLaptopBinding.laptop = laptops[position]
        holder.itemLaptopBinding.root.setOnClickListener {
            listener.onRecyclerViewItemClick(laptops[position])
        }
    }

    override fun getItemCount(): Int = laptops.size
}