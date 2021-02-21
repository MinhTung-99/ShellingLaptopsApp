package com.shellinglaptop.viewmodel;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import com.shellinglaptop.model.User;
import com.shellinglaptop.network.RetrofitInstance;
import com.shellinglaptop.network.UserApi;
import com.shellinglaptop.utils.UserUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    private User userRegister;
    private MutableLiveData<Boolean> isEnoughEditText;
    private MutableLiveData<Boolean> isProgressbar;

    public MutableLiveData<Boolean> getIsProgressbar() {
        return isProgressbar;
    }
    public MutableLiveData<Boolean> getIsEnoughEditText() {
        return isEnoughEditText;
    }
    public RegisterViewModel() {
        userRegister = new User("","","","");
        isEnoughEditText = new MutableLiveData<>(false);
        isProgressbar = new MutableLiveData<>(false);
    }
    public void checkEditText(){
        if(!userRegister.getUserName().equals("") && userRegister.getPassword().length() >= 6 &&
                !userRegister.getFullName().equals("") && userRegister.getPhoneNumber().length() == 10){
            isEnoughEditText.postValue(true);
        }else{
            isEnoughEditText.postValue(false);
        }
    }
    public void registerApiCall(View view){
        isProgressbar.postValue(true);
        userRegister.setTypeUser(UserUtils.USER);
        UserApi userApi = RetrofitInstance.getRetrofitClient().create(UserApi.class);
        userApi.register(userRegister).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    if(response.body()){
                        isProgressbar.postValue(false);
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
    public void setUserName(Editable editable){
        userRegister.setUserName(editable.toString());
        checkEditText();
    }
    public void setPassword(Editable editable){
        userRegister.setPassword(editable.toString());
        checkEditText();
    }
    public void setFullName(Editable editable){
        userRegister.setFullName(editable.toString());
        checkEditText();
    }
    public void setPhoneNumber(Editable editable){
        userRegister.setPhoneNumber(editable.toString());
        checkEditText();
    }
    public void btnRegister(View view){
        registerApiCall(view);
    }
}
