package com.example.shellinglaptopapp.data.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_cart")
open class Cart: Laptop{

    @SerializedName("amount")
    @Expose
    var amount: Int? = null

    @SerializedName("intoMoney")
    @Expose
    var totalMoney: Long? = null

    var totalMoneyStr: String? = null

    constructor(image: String,name: String, hardDrive: String, ram: String,
                price: Long ,priceStr: String, amount: Int, totalMoney: Long, totalMoneyStr: String){
        this.image = image
        this.name = name
        this.hardDrive = hardDrive
        this.ram = ram
        this.price = price
        this.priceStr = priceStr
        this.amount = amount
        this.totalMoney = totalMoney
        this.totalMoneyStr = totalMoneyStr
    }
}