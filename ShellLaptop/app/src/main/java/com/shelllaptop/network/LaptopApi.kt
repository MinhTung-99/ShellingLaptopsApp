package com.shelllaptop.network

import com.shelllaptop.model.Laptop
import com.shelllaptop.model.MyArray
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
                .baseUrl("http://192.168.4.100:8080/")
                .build()
                .create(LaptopApi::class.java)
        }
    }
}