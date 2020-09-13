package com.example.shellinglaptopapp.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shellinglaptopapp.data.repository.LaptopRepository

class PayViewModelFactory(
    private val repository: LaptopRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PayViewModel(repository) as T
    }
}