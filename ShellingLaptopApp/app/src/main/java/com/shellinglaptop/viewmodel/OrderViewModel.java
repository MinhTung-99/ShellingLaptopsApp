package com.shellinglaptop.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shellinglaptop.model.Cart;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.model.Order;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {

    private String name;
    private String address;
    private String phone;
    private Cart cart;
    private MutableLiveData<Boolean> isSetOrder;

    public OrderViewModel() {
        isSetOrder = new MutableLiveData<>();
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void btnOderOnClick(View view){
        LaptopApi laptopApi = RetrofitInstance.getRetrofitClient().create(LaptopApi.class);
//        Order order = new Order(cart.getLaptopId(),name,address,phone,cart.getCount(),cart.getTotalMoneyStr());
//        laptopApi.setOrder(order).enqueue(new Callback<Order>() {
//            @Override
//            public void onResponse(Call<Order> call, Response<Order> response) {
//                isSetOrder.postValue(true);
//            }
//
//            @Override
//            public void onFailure(Call<Order> call, Throwable t) {
//                isSetOrder.postValue(false);
//            }
//        });
    }

    public void afterNameChange(CharSequence s){
        name = s.toString();
    }

    public void afterAddressChange(CharSequence s){
        address = s.toString();
    }

    public void afterPhoneChange(CharSequence s){
        phone = s.toString();
    }

    public MutableLiveData<Boolean> getIsSetOrder() {
        return isSetOrder;
    }
}
