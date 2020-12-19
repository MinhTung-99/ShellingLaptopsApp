package com.shellinglaptop.network;

import com.shellinglaptop.model.LaptopList;
import com.shellinglaptop.model.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LaptopApi {

    @GET("laptop")
    Call<LaptopList> getLaptops();

    @POST("neworder")
    Call<Order> setOrder(@Body Order order);
}
