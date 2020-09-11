package com.example.shellinglaptopapp.data.network

import com.example.shellinglaptopapp.data.model.MyArray
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface LaptopApi {

    @GET("laptop")
    suspend fun getLaptops(): Response<MyArray>

    companion object {
        operator fun invoke(): LaptopApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://shellinglaptop.herokuapp.com/")
                .build()
                .create(LaptopApi::class.java)
        }
    }
}