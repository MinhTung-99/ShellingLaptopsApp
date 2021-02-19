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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.shellinglaptop.R;
import com.shellinglaptop.databinding.FragmentLaptopBinding;
import com.shellinglaptop.databinding.FragmentLoginBinding;
import com.shellinglaptop.model.User;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;
import com.shellinglaptop.network.UserApi;
import com.shellinglaptop.utils.UserUtils;
import com.shellinglaptop.viewmodel.LaptopViewModel;
import com.shellinglaptop.viewmodel.LoginViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding  = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.setContext(requireContext());
    }
}
