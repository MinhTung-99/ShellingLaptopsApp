package com.example.shellinglaptopapp.ui.bert

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BertViewModelFactory(
    private val context: Context
) : ViewModelProvider.NewInstanceFactory(){

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BertViewModel(context) as T
    }
}