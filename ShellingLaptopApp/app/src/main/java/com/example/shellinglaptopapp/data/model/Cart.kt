package com.example.shellinglaptopapp.data.model

import androidx.room.Entity

@Entity(tableName = "table_cart")
class Cart: Laptop{

    var amount: Int? = null
    var totalMoney: Long? = null
    var totalMoneyStr: String? = null

    constructor(image: String,name: String, hardDrive: String, ram: String,
                priceStr: String, amount: Int, totalMoney: Long, totalMoneyStr: String){
        this.image = image
        this.name = name
        this.hardDrive = hardDrive
        this.ram = ram
        this.priceStr = priceStr
        this.amount = amount
        this.totalMoney = totalMoney
        this.totalMoneyStr = totalMoneyStr
    }
}