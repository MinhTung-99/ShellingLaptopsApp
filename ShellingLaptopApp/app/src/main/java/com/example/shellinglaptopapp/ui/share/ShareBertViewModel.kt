package com.example.shellinglaptopapp.ui.share

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.data.model.Bert
import com.example.shellinglaptopapp.data.model.Cart

class ShareBertViewModel : ViewModel() {
    val bert = MutableLiveData<Bert>()
}