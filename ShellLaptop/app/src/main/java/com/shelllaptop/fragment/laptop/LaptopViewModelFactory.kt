package com.shelllaptop.fragment.laptop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LaptopViewModelFactory(
    private val repository: LaptopRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaptopViewModel(repository) as T
    }
}