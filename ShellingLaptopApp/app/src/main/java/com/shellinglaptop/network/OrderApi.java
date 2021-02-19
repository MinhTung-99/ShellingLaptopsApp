package com.shellinglaptop.network;

import com.shellinglaptop.model.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi {

    @POST("/api-new-order/{userName}/{password}")
    Call<Boolean> saveOrder(@Body Order order, @Path("userName") String userName, @Path("password") String password);
}
