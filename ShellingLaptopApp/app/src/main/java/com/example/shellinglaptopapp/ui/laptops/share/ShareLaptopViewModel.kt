package com.example.shellinglaptopapp.ui.laptops.share

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.data.model.Laptop

class ShareLaptopViewModel : ViewModel() {

    var laptop = MutableLiveData<Laptop>()
}