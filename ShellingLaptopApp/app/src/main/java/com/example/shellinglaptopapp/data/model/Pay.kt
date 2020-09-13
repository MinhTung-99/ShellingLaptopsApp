package com.example.shellinglaptopapp.data.model

import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pay: Cart {

    @SerializedName("fullName")
    @Expose
    var fullName: String? = null

    @SerializedName("phoneNumber")
    @Expose
    var phoneNumber: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    constructor(image: String,name: String, hardDrive: String, ram: String,
                price: Long, amount: Int, totalMoney: Long, fullName: String,
                phoneNumber: String, email: String, address: String) :
            super(image, name, hardDrive, ram, price, "" ,amount, totalMoney,"") {
        this.fullName = fullName
        this.phoneNumber = phoneNumber
        this.email = email
        this.address = address
        Log.e("KMFFFT","TTTT ${email}")
    }
}