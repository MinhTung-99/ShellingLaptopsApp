package com.example.shellinglaptopapp.api

import com.example.shellinglaptopapp.model.Laptop
import com.example.shellinglaptopapp.model.MyArray
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface LaptopApi {

    @GET("laptop")
    suspend fun getLaptops(): Response<MyArray>

    companion object {
        operator fun invoke(): LaptopApi{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://shellingtest.herokuapp.com/")
                .build()
                .create(LaptopApi::class.java)
        }
    }
}