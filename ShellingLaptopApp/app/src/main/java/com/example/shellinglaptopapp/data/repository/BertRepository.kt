package com.example.shellinglaptopapp.data.repository

import com.example.shellinglaptopapp.data.model.Bert
import com.example.shellinglaptopapp.data.network.BertApi

class BertRepository(
    private val api: BertApi
) : SafeApiRequest() {

    suspend fun postBert(bert: Bert) = apiRequest { api.postBert(bert) }

    suspend fun getBert() = apiRequest { api.getBert() }
}