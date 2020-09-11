package com.example.shellinglaptopapp.ui.laptops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.data.model.Laptop
import com.example.shellinglaptopapp.data.repository.LaptopRepository
import com.example.shellinglaptopapp.util.Coroutines
import com.example.shellinglaptopapp.data.model.MyArray
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