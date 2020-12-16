package com.shelllaptop.fragment.laptop

import com.shelllaptop.network.LaptopApi
import com.shelllaptop.network.SafeApiRequest

class LaptopRepository (
    private val api: LaptopApi) : SafeApiRequest()
{
    suspend fun getLaptops() = apiRequest { api.getLaptops() }
}