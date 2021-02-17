package com.shellinglaptop.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingUtils {
    @BindingAdapter("imageStr")
    public static void loadImage(ImageView imageView, String url){
        Bitmap bitmap = ImageUtils.convertStringToBitmap(url);
        imageView.setImageBitmap(bitmap);
    }

}
