package com.shellinglaptop.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.shellinglaptop.R;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.model.LaptopList;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;
import com.shellinglaptop.utils.ConstantUtils;
import com.shellinglaptop.utils.Hide;
import com.shellinglaptop.utils.ImageUtils;
import com.shellinglaptop.utils.UserUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaptopViewModel extends ViewModel {

    private MutableLiveData<List<Laptop>> laptops;
    private MutableLiveData<Boolean> isUpdateOrAdd;
    private MutableLiveData<Boolean> isDelete;
    private Laptop laptop;
    private LaptopApi laptopApi;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }
    public LaptopViewModel() {
        laptops = new MutableLiveData<>();
        isUpdateOrAdd = new MutableLiveData<>();
        isDelete = new MutableLiveData<>();
        laptopApi = RetrofitInstance.getRetrofitClient().create(LaptopApi.class);
    }
    public MutableLiveData<List<Laptop>> getLaptops() {
        return laptops;
    }
    public MutableLiveData<Boolean> getIsUpdateOrAdd() {
        return isUpdateOrAdd;
    }
    public MutableLiveData<Boolean> getIsDelete() {
        return isDelete;
    }
    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
    public void moveCartFragment(View view){
       Navigation.findNavController(view).navigate(R.id.cartFragment);
    }
    public void moveUpdateOrAddFragment(View view){
        Bundle bundle = new Bundle();
        Laptop laptop = new Laptop();
        laptop.setTypeUpdate(ConstantUtils.ADD);
        bundle.putSerializable("laptop", laptop);
        Navigation.findNavController(view).navigate(R.id.updateOrAddLaptopAdminFragment, bundle);
    }
    public void updateOrAdd(View view){
        isUpdateOrAdd.postValue(false);
        Hide.keyboardFrom(context, view);
        if(laptop.getTypeUpdate() == ConstantUtils.Update){
            updateLaptopApiCall(view);
        }else{
           saveLaptopApiCall(view);
        }
    }
    public void setName(Editable editable){
        laptop.setName(editable.toString());
    }
    public void setSale(Editable editable){
        laptop.setSale(editable.toString());
    }
    public void setPrice(Editable editable){
        if(editable.toString().equals("")){
            laptop.setPrice(0L);
        }else{
            laptop.setPrice(Long.parseLong(editable.toString()));
        }
    }
    public void setDescription(Editable editable){
        laptop.setDescription(editable.toString());
    }
    public void laptopApiCall(){
        if(laptops.getValue() != null)
            laptops.getValue().clear();
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
    public void deleteLaptopApiCall(Laptop laptop){
        isDelete.postValue(true);
        laptopApi.deleteLaptop(laptop, UserUtils.userName, UserUtils.password).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    if(response.body()){
                        isDelete.postValue(false);
                        laptopApiCall();
                    }
                }else{
                    Log.d("KMFG", "FAILED="+response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("KMFG", "ERR="+t.getMessage());
            }
        });
    }
    private void saveLaptopApiCall(View view){
        laptopApi.saveLaptop(laptop, UserUtils.userName, UserUtils.password).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    if(response.body()){
                        isUpdateOrAdd.postValue(true);
                        Navigation.findNavController(view).popBackStack();
                    }
                }else{
                    Log.d("KMFG", "FAILED="+response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("KMFG", "ERR="+t.getMessage());
            }
        });
    }
    private void updateLaptopApiCall(View view){
        laptopApi.updateLaptop(laptop, laptop.getLaptopId(),UserUtils.userName, UserUtils.password).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    Log.d("KMFG", "OKEE="+response.body());
                    if(response.body()){
                        isUpdateOrAdd.postValue(true);
                        Navigation.findNavController(view).popBackStack();
                    }
                }else{
                    Log.d("KMFG", "FAILED="+response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("KMFG", "ERR="+t.getMessage());
            }
        });
    }
}
