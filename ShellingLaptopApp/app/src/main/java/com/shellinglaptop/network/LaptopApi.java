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
    @GET("api-laptop")
    Call<LaptopList> getLaptops();
    @POST("api-new-laptop/{userName}/{password}")
    Call<Boolean> saveLaptop(@Body Laptop laptop, @Path("userName") String userName, @Path("password") String password);
    @PUT("api-update-laptop/{laptopId}/{userName}/{password}")
    Call<Boolean> updateLaptop(@Body Laptop laptop, @Path("laptopId") Long laptopid,@Path("userName") String userName, @Path("password") String password);
    @HTTP(method = "DELETE", path = "delete/{userName}/{password}", hasBody = true)
    Call<Void> deleteLaptop(@Body Laptop laptop, @Path("userName") String userName, @Path("password") String password);
}
