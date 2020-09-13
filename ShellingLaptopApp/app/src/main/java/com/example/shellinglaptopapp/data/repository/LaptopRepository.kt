package com.example.shellinglaptopapp.data.repository

import com.example.shellinglaptopapp.data.model.Pay
import com.example.shellinglaptopapp.data.network.LaptopApi

class LaptopRepository(
    private val api: LaptopApi
) : SafeApiRequest() {

    suspend fun getLaptops() = apiRequest { api.getLaptops() }

    suspend fun postPays(pay: Pay) = apiRequest { api.postPays(pay) }
}