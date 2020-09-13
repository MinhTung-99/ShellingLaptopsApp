package com.example.shellinglaptopapp.ui.order

import androidx.lifecycle.ViewModel
import com.example.shellinglaptopapp.data.model.Pay
import com.example.shellinglaptopapp.data.repository.LaptopRepository
import com.example.shellinglaptopapp.util.Coroutines
import kotlinx.coroutines.Job

class PayViewModel(
    private val repository: LaptopRepository
): ViewModel() {

    private lateinit var job: Job

    fun postPay(pay: Pay){
        job = Coroutines.ioThenMain(
            {repository.postPays(pay)},
            {}
        )
    }

    override fun onCleared() {
        super.onCleared()

        if(::job.isInitialized) job.cancel()
    }
}