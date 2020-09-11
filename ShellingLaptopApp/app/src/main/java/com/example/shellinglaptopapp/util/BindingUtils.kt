package com.example.shellinglaptopapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImageLaptop(view: ImageView, url: String){
    Glide.with(view.context)
        .load(url)
        .into(view)
}