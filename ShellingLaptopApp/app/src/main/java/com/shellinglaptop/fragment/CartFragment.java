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
import com.shellinglaptop.adapter.CartAdapter;
import com.shellinglaptop.databinding.FragmentCartBinding;
import com.shellinglaptop.model.Cart;
import com.shellinglaptop.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private CartViewModel viewModel;
    private FragmentCartBinding binding;
    private CartAdapter adapter;
    private List<Cart> carts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        viewModel.setContext(getActivity().getApplicationContext());
        viewModel.getCarts().observe(getViewLifecycleOwner(), carts -> {
            adapter.setCarts(carts);
        });
    }

    private void setAdapter() {
        carts = new ArrayList<>();
        adapter = new CartAdapter(carts);
        binding.rvCart.setAdapter(adapter);
    }
}
