package com.example.shellinglaptopapp.util

import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String){
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("app:textWatcher")
fun watcher(view: EditText, textWatcher: TextWatcher){
    view.addTextChangedListener(textWatcher)
}