package com.example.shellinglaptopapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.databinding.ItemLaptopBinding
import com.example.shellinglaptopapp.model.Laptop
import com.example.shellinglaptopapp.model.MyArray

class LaptopAdapter(
    private val laptops: List<Laptop>,
): RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>() {

    class LaptopViewHolder(
        val itemLaptopBinding: ItemLaptopBinding
    ) : RecyclerView.ViewHolder(itemLaptopBinding.root)
    {
        object imageUrl{
            @BindingAdapter("imageUrl")
            @JvmStatic
            fun setImageSong(imageView: ImageView, url: String){
                Glide.with(imageView.context)
                    .load(url)
                    .into(imageView);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopViewHolder =
        LaptopViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_laptop,
                parent, false)
        )

    override fun onBindViewHolder(holder: LaptopViewHolder, position: Int) {
        holder.itemLaptopBinding.laptop = laptops[position]
    }

    override fun getItemCount(): Int = laptops.size
}