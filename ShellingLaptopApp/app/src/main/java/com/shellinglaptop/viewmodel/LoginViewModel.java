package com.shellinglaptop.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import com.shellinglaptop.R;
import com.shellinglaptop.model.User;
import com.shellinglaptop.network.RetrofitInstance;
import com.shellinglaptop.network.UserApi;
import com.shellinglaptop.utils.Hide;
import com.shellinglaptop.utils.UserUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private User userLogin;
    private SharedPreferences sharedPreferences;
    private Context context;
    private MutableLiveData<Boolean> isProgressBar;
    private MutableLiveData<Boolean> isEnoughEditText;

    public MutableLiveData<Boolean> getIsEnoughEditText() {
        return isEnoughEditText;
    }
    public MutableLiveData<Boolean> getIsProgressBar() {
        return isProgressBar;
    }
    public LoginViewModel() {
        userLogin = new User("","");
        isProgressBar = new MutableLiveData<>(false);
        isEnoughEditText = new MutableLiveData<>(false);
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public void checkEditText(){
        if(!userLogin.getUserName().equals("") && userLogin.getPassword().length() >= 6){
            isEnoughEditText.postValue(true);
        }else{
            isEnoughEditText.postValue(false);
        }
    }
    public void btnLogin(View view){
        isProgressBar.postValue(true);
        Hide.keyboardFrom(context, view);
        UserApi userApi = RetrofitInstance.getRetrofitClient().create(UserApi.class);
        userApi.login(userLogin).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        isProgressBar.postValue(false);
                        UserUtils.userName = userLogin.getUserName();
                        UserUtils.password = userLogin.getPassword(); 
                        if(response.body().getTypeUser().equals(UserUtils.ADMIN)){
                            Navigation.findNavController(view).navigate(R.id.laptopAdminFragment);
                        }else if(response.body().getTypeUser().equals(UserUtils.USER)){
                            saveSharedPreferences(response);
                            Navigation.findNavController(view).popBackStack();
                        }else if(response.body().getTypeUser().equals("NULL")){
                            Toast.makeText(context, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Log.d("KMFG", "FAILED="+response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("KMFG", "ERR="+t.getMessage());
            }
        });
    }
    public void btnRegister(View view){
        Navigation.findNavController(view).navigate(R.id.registerFragment);
    }
    public void setUserName(Editable editable){
        userLogin.setUserName(editable.toString());
        checkEditText();
    }
    public void setPassword(Editable editable){
        userLogin.setPassword(editable.toString());
        checkEditText();
    }
    private void saveSharedPreferences(Response<User> response){
        sharedPreferences = context.getSharedPreferences(UserUtils.MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(UserUtils.USER_ID, response.body().getUserId());
        editor.putString(UserUtils.FULL_NAME, response.body().getFullName());
        editor.putString(UserUtils.PHONE_NUMBER, response.body().getPhoneNumber());
        editor.putString(UserUtils.USER_NAME, userLogin.getUserName());
        editor.putString(UserUtils.PASSWORD, userLogin.getPassword());
        editor.commit();
    }
}
