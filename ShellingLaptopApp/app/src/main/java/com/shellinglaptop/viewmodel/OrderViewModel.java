package com.shellinglaptop.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.model.Order;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {

    public void btnOderOnClick(View view){
        LaptopApi laptopApi = RetrofitInstance.getRetrofitClient().create(LaptopApi.class);
        Order order = new Order(1,"Tung","HN","09873333",3,19993);
        laptopApi.setOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.d("KMGH", "CLICKED OK");
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d("KMGH", t.getMessage()+" =");
            }
        });
    }

}
