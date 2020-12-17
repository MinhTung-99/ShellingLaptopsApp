package com.shellinglaptop.utils;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingUtils {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url){
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }
}
