package com.example.shellinglaptopapp.api

class LaptopRepository(
    private val api: LaptopApi
) : SafeApiRequest() {

    suspend fun getLaptops() = apiRequest { api.getLaptops() }
}