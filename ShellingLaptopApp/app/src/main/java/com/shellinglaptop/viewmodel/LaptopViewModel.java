package com.shellinglaptop.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.shellinglaptop.R;
import com.shellinglaptop.model.LaptopList;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaptopViewModel extends ViewModel {

    private MutableLiveData<LaptopList> laptops;

    public LaptopViewModel() {
        laptops = new MutableLiveData<>();
    }

    public MutableLiveData<LaptopList> getLaptops() {
        return laptops;
    }

    public void moveCartFragment(View view){
       // Navigation.findNavController(view).navigate(R.id.cartFragment);
    }

    public void laptopApiCall(){
        LaptopApi laptopApi = RetrofitInstance.getRetrofitClient().create(LaptopApi.class);
//        Call<LaptopList> call = laptopApi.getLaptops();
//        call.enqueue(new Callback<LaptopList>() {
//            @Override
//            public void onResponse(Call<LaptopList> call, Response<LaptopList> response) {
//                laptops.postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<LaptopList> call, Throwable t) {
//                laptops.postValue(null);
//            }
//        });
    }
}
