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

import com.shellinglaptop.databinding.FragmentOrderBinding;
import com.shellinglaptop.model.Cart;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.viewmodel.OrderViewModel;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;
    private OrderViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Cart cart = (Cart) getArguments().getSerializable("cart");

        viewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.setCart(cart);
        viewModel.getIsSetOrder().observe(getViewLifecycleOwner(), isSetOrder -> {
            if(isSetOrder){
                message("Đặt hàng thành công");
            }else {
                message("Đặt hàng thất bại");
            }
        });
    }

    private void message(String str){
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
