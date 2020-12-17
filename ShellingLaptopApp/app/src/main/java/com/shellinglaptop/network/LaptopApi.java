package com.shellinglaptop.network;

import com.shellinglaptop.model.LaptopList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LaptopApi {

    @GET("laptop")
    Call<LaptopList> getLaptops();
}
