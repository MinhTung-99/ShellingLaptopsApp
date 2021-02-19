package com.shellinglaptop.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import com.shellinglaptop.R;
import com.shellinglaptop.model.User;
import com.shellinglaptop.network.RetrofitInstance;
import com.shellinglaptop.network.UserApi;
import com.shellinglaptop.utils.UserUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private User user = new User();
    private SharedPreferences sharedPreferences;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }
    public void login(View view){
        UserApi userApi = RetrofitInstance.getRetrofitClient().create(UserApi.class);
        userApi.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        if(response.body().getTypeUser().equals(UserUtils.ADMIN)){
                            UserUtils.userName = user.getUserName();
                            UserUtils.password = user.getPassword();
                            Navigation.findNavController(view).navigate(R.id.laptopAdminFragment);
                        }else if(response.body().getTypeUser().equals(UserUtils.USER)){
                            saveSharedPreferences(response);
                            Navigation.findNavController(view).popBackStack();
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
    public void setUserName(Editable editable){
        user.setUserName(editable.toString());
    }
    public void setPassword(Editable editable){
        user.setPassword(editable.toString());
    }
    private void saveSharedPreferences(Response<User> response){
        sharedPreferences = context.getSharedPreferences(UserUtils.MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(UserUtils.USER_ID, response.body().getUserId());
        editor.putString(UserUtils.FULL_NAME, response.body().getFullName());
        editor.putInt(UserUtils.PHONE_NUMBER, response.body().getPhoneNumber());
        editor.putString(UserUtils.USER_NAME, user.getUserName());
        editor.putString(UserUtils.PASSWORD, user.getPassword());
        editor.commit();
    }
}
