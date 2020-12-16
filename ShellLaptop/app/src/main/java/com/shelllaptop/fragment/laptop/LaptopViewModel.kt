package com.shelllaptop.fragment.laptop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shelllaptop.model.MyArray
import com.shelllaptop.util.Coroutines
import kotlinx.coroutines.Job

class LaptopViewModel(
    private val repository: LaptopRepository
): ViewModel() {
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