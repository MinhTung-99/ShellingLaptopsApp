package com.example.shellinglaptopapp.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Laptop
{
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @SerializedName("brand")
    @Expose
    @ColumnInfo(name = "brand")
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

    var priceStr: String? = null
}