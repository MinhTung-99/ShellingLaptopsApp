package com.example.shellinglaptopapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyArray {

    @SerializedName("laptops")
    @Expose
    val informationLaptop: List<Laptop>? = null
}