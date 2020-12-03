package com.example.shellinglaptopapp.util

import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
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

@BindingAdapter("android:alignParentRight")
fun setLayoutAlignParentRight(view: View, parent: Boolean){
    val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
    layoutParams.addRule(
        RelativeLayout.ALIGN_PARENT_RIGHT,
        if (parent) RelativeLayout.TRUE else 0
    )
    view.layoutParams = layoutParams
}