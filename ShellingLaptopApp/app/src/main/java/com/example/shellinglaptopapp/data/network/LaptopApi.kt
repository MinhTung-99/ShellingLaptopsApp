package com.example.shellinglaptopapp.data.network

import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.data.model.MyArray
import com.example.shellinglaptopapp.data.model.Pay
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface LaptopApi {

    @GET("laptop")
    suspend fun getLaptops(): Response<MyArray>

    @POST("pay")
    suspend fun postPays(@Body pay: Pay): Response<Cart>

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