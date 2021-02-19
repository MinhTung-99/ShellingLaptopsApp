package com.shellinglaptop.network;

import com.shellinglaptop.model.User;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("login")
    Call<User> login(@Body User user);
}
