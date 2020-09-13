package com.example.shellinglaptopapp.ui.share

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.data.model.Cart

class ShareCartViewModel: ViewModel() {

    val cart = MutableLiveData<Cart>()
}