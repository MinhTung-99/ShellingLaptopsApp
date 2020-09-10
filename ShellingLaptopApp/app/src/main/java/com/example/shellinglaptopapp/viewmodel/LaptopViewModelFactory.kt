package com.example.shellinglaptopapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shellinglaptopapp.api.LaptopRepository

@Suppress("UNCHECKED_CAST")
class LaptopViewModelFactory(
    private val repository: LaptopRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaptopViewModel(repository) as T
    }
}