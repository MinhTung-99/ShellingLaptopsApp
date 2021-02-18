package com.shellinglaptop.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.shellinglaptop.R;
import com.shellinglaptop.databinding.FragmentLaptopBinding;
import com.shellinglaptop.databinding.FragmentLoginBinding;
import com.shellinglaptop.model.User;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;
import com.shellinglaptop.network.UserApi;
import com.shellinglaptop.utils.UserUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding  = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLogin.setOnClickListener(v->{
            User user = new User();
            user.setUserName(binding.edtUsername.getText().toString());
            user.setPassword(binding.edtPassword.getText().toString());
            UserApi userApi = RetrofitInstance.getRetrofitClient().create(UserApi.class);
            userApi.login(user).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        Log.d("KMFG", "OKEE"+response.body());
                        if(response.body().equals(UserUtils.ADMIN)){
                            UserUtils.userName = user.getUserName();
                            UserUtils.password = user.getPassword();
                            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.laptopAdminFragment);
                        }else if(response.body().equals(UserUtils.USER)){
                            sharedPreferences = getContext().getSharedPreferences(UserUtils.MY_PREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(UserUtils.USER_NAME, user.getUserName());
                            editor.putString(UserUtils.PASSWORD, user.getPassword());
                            editor.commit();
                            NavHostFragment.findNavController(LoginFragment.this).popBackStack();
                        }
                    }else{
                        Log.d("KMFG", "FAILED="+response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("KMFG", "ERR="+t.getMessage());
                }
            });
        });
    }
}
