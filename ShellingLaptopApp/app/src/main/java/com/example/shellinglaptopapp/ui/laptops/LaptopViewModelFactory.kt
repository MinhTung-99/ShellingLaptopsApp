package com.example.shellinglaptopapp.ui.laptops

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shellinglaptopapp.data.repository.LaptopRepository

@Suppress("UNCHECKED_CAST")
class LaptopViewModelFactory(
    private val repository: LaptopRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaptopViewModel(repository) as T
    }
}