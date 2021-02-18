package com.shellinglaptop.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.shellinglaptop.R;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.model.LaptopList;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaptopViewModel extends ViewModel {

    private MutableLiveData<List<Laptop>> laptops;

    public LaptopViewModel() {
        laptops = new MutableLiveData<>();
    }

    public MutableLiveData<List<Laptop>> getLaptops() {
        return laptops;
    }

    public void moveCartFragment(View view){
       Navigation.findNavController(view).navigate(R.id.cartFragment);
    }

    public void laptopApiCall(){
        LaptopApi laptopApi = RetrofitInstance.getRetrofitClient().create(LaptopApi.class);
        laptopApi.getLaptops().enqueue(new Callback<LaptopList>() {
            @Override
            public void onResponse(Call<LaptopList> call, Response<LaptopList> response) {
                if(response.isSuccessful()){
                    laptops.postValue(response.body().getLaptops());
                }
            }

            @Override
            public void onFailure(Call<LaptopList> call, Throwable t) {
                Log.d("KMFG", "ERR=" + t.getMessage());
            }
        });
    }
}
