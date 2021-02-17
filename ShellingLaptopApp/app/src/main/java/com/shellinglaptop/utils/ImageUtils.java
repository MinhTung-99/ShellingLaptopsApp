package com.shellinglaptop.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class ImageUtils {
    public static Bitmap convertStringToBitmap(String strImage){
        byte [] encodeByte = Base64.decode(strImage,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte , 0, encodeByte.length);
        return bitmap;
    }
}
