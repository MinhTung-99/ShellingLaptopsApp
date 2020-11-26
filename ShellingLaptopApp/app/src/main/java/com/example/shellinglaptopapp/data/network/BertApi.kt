package com.example.shellinglaptopapp.data.network

import com.example.shellinglaptopapp.data.model.Bert
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BertApi {
    @GET("bert")
    suspend fun getBert(): Response<String>

    @POST("bert")
    suspend fun postBert(@Body bert: Bert): Response<Bert>

    companion object {
        operator fun invoke(): BertApi {

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://192.168.4.100:8080/")
                .build()
                .create(BertApi::class.java)
        }
    }
}