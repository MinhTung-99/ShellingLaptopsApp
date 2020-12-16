package com.shelllaptop.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Laptop {
    @SerializedName("laptopId")
    @Expose
    var laptopId: Long? = null

    @SerializedName("brand")
    @Expose
    var brand: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("cpu")
    @Expose
    var cpu: String? = null

    @SerializedName("cardGraphic")
    @Expose
    var cardGraphic: String? = null

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

    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null

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
    var price: Float? = null
}