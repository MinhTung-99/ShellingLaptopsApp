package com.shellinglaptop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.shellinglaptop.R;
import com.shellinglaptop.adapter.CartAdapter;
import com.shellinglaptop.databinding.FragmentCartBinding;
import com.shellinglaptop.db.CartDao;
import com.shellinglaptop.db.CartDatabase;
import com.shellinglaptop.model.Cart;
import com.shellinglaptop.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.ButtonOnClickListener{

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
            if(carts.size() == 0){
                Animation ani = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.ani_cart);
                binding.txtCart.startAnimation(ani);
            }else{
                binding.txtCart.setVisibility(View.GONE);
                adapter.setCarts(carts);
            }
        });
    }

    private void setAdapter() {
        carts = new ArrayList<>();
        adapter = new CartAdapter(carts, this);
        binding.rvCart.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.txtCart.clearAnimation();
    }

    @Override
    public void btnDeleteOnClick(Cart cart) {
        CartDao cartDao = CartDatabase.getInstance(getContext()).cartDao();
        cartDao.deleteCart(cart);
        if(cartDao.getCart().size() == 0){
            Animation ani = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.ani_cart);
            binding.txtCart.startAnimation(ani);
        }
        adapter.setCarts(cartDao.getCart());
    }

    @Override
    public void btnOrderOnClick(Cart cart) {
       // Bundle bundle = new Bundle();
       // bundle.putSerializable("cart", cart);
       // NavHostFragment.findNavController(this).navigate(R.id.orderFragment, bundle);


    }
}
