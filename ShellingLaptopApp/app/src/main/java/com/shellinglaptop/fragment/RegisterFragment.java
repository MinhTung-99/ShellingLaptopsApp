package com.shellinglaptop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.shellinglaptop.databinding.FragmentRegisterBinding;
import com.shellinglaptop.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding  = FragmentRegisterBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.checkEditText();

        viewModel.getIsEnoughEditText().observe(getViewLifecycleOwner(), isEnoughEditText->{
            binding.btnRegister.setEnabled(isEnoughEditText);
        });
        viewModel.getIsProgressbar().observe(getViewLifecycleOwner(), isProgressbar->{
            if(isProgressbar){
                binding.progressBar.setVisibility(View.VISIBLE);
            }else{
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}
