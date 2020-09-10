package com.example.shellinglaptopapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.api.LaptopRepository
import com.example.shellinglaptopapp.coroutine.Coroutines
import com.example.shellinglaptopapp.model.Laptop
import com.example.shellinglaptopapp.model.MyArray
import kotlinx.coroutines.Job

class LaptopViewModel(
    private val repository: LaptopRepository
): ViewModel() {

    //coroutine
    private lateinit var job: Job

    private val _laptops = MutableLiveData<MyArray>()
    val laptops: LiveData<MyArray>
        get() = _laptops

    fun getLaptops(){
        job = Coroutines.ioThenMain(
            {repository.getLaptops()},
            {
                _laptops.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()

        if(::job.isInitialized) job.cancel()
    }
}