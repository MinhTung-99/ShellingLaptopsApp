package com.shellinglaptop.network;

import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.model.LaptopList;
import com.shellinglaptop.model.Order;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface LaptopApi {
    @GET("laptop")
    Call<LaptopList> getLaptops();
    @POST("neworder")
    Call<Order> setOrder(@Body Order order);
    @POST("api-new-laptop")
    Call<Void> saveLaptop(@Body Laptop laptop);
    @PUT("/api-update-laptop/{laptopId}")
    Call<ResponseBody> updateLaptop(@Body Laptop laptop, @Path("laptopId") Long laptopid);
    @HTTP(method = "DELETE", path = "/delete", hasBody = true)
    Call<Void> deleteLaptop(@Body Laptop laptop);
}
