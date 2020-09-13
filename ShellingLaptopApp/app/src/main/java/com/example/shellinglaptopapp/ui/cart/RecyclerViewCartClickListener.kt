package com.example.shellinglaptopapp.ui.cart

import com.example.shellinglaptopapp.data.model.Cart

interface RecyclerViewCartClickListener {
    fun orderOnClick(cart: Cart)
    fun deleteOnClick(cart: Cart)
}