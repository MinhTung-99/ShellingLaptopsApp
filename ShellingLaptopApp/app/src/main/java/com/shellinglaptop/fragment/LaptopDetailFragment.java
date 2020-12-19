package com.shellinglaptop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.shellinglaptop.R;
import com.shellinglaptop.databinding.FragmentDetailLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.viewmodel.LaptopDetailViewModel;
import com.shellinglaptop.viewmodel.LaptopViewModel;

public class LaptopDetailFragment extends Fragment {

    private FragmentDetailLaptopBinding binding;
    private LaptopDetailViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailLaptopBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Laptop laptop = (Laptop) getArguments().getSerializable("laptop");
        binding.setLaptop(laptop);

        viewModel = new ViewModelProvider(this).get(LaptopDetailViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.setLaptops(laptop);
        viewModel.setContext(getContext());

        viewModel.getCount().observe(getViewLifecycleOwner(), count -> {
            binding.txtCount.setText(count.toString());
        });
    }
}
