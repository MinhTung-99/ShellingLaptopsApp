package com.example.shellinglaptopapp.ui.bert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shellinglaptopapp.data.repository.BertRepository
import com.example.shellinglaptopapp.ui.laptops.LaptopViewModel

class BertViewModelFactory(
    private val repository: BertRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BertViewModel(repository) as T
    }
}