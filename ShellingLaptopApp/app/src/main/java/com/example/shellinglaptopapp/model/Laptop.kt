package com.example.shellinglaptopapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Laptop(brand: String)
{
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("brand")
    @Expose
    var brand: String? = brand

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("cpu")
    @Expose
    var cpu: String? = null

    @SerializedName("cardGraphics")
    @Expose
    var cardGraphics: String? = null

    @SerializedName("hardDrive")
    @Expose
    var hardDrive: String? = null

    @SerializedName("ram")
    @Expose
    var ram: String? = null

    @SerializedName("display")
    @Expose
    var display: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("weight")
    @Expose
    var weight: String? = null

    @SerializedName("color")
    @Expose
    var color: String? = null

    @SerializedName("os")
    @Expose
    var os: String? = null

    @SerializedName("pin")
    @Expose
    var pin: String? = null

    @SerializedName("price")
    @Expose
    var price: Long? = null

    @SerializedName("priceStr")
    @Expose
    var priceStr: String? = null
}