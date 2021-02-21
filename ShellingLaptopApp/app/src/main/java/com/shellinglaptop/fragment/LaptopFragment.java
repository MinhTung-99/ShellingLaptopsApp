package com.shellinglaptop.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.shellinglaptop.R;
import com.shellinglaptop.SplashActivity;
import com.shellinglaptop.adapter.LaptopAdapter;
import com.shellinglaptop.databinding.FragmentLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.utils.UserUtils;
import com.shellinglaptop.viewmodel.LaptopViewModel;

import java.util.ArrayList;
import java.util.List;

public class LaptopFragment extends Fragment implements LaptopAdapter.RecyclerViewLaptopClickListener {

    private LaptopViewModel viewModel;
    private FragmentLaptopBinding binding;
    private LaptopAdapter adapter;
    private List<Laptop> laptops;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding  = FragmentLaptopBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getContext().getSharedPreferences(UserUtils.MY_PREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(UserUtils.USER_NAME)){
            UserUtils.userName = sharedPreferences.getString(UserUtils.USER_NAME, null);
            UserUtils.password = sharedPreferences.getString(UserUtils.PASSWORD, null);
            String fullName = sharedPreferences.getString(UserUtils.FULL_NAME, null);;
            binding.txtUsername.setText("Xin chào " + fullName);
            binding.txtUsername.setVisibility(View.VISIBLE);
            binding.imgLogin.setImageResource(R.drawable.ic_logout);
        }else {
            binding.txtUsername.setVisibility(View.GONE);
            binding.imgLogin.setImageResource(R.drawable.ic_login);
        }
        viewGone();
        setAdapter();
        binding.imgLogin.setOnClickListener(v->{
            if(sharedPreferences.contains(UserUtils.USER_NAME)){
                removeSharedPreferences(UserUtils.USER_ID);
                removeSharedPreferences(UserUtils.FULL_NAME);
                removeSharedPreferences(UserUtils.PHONE_NUMBER);
                removeSharedPreferences(UserUtils.USER_NAME);
                removeSharedPreferences(UserUtils.PASSWORD);
                UserUtils.userName = null;
                UserUtils.password = null;
                binding.txtUsername.setVisibility(View.GONE);
                binding.imgLogin.setImageResource(R.drawable.ic_login);
            }else{
                NavHostFragment.findNavController(this).navigate(R.id.loginFragment);
            }
        });
        viewModel = new ViewModelProvider(this).get(LaptopViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.laptopApiCall();
        viewModel.getLaptops().observe(getViewLifecycleOwner(), laptopList -> {
            if(laptopList != null){
                viewVisible();
                binding.progressBar.setVisibility(View.GONE);
                adapter.setLaptops(laptopList);
            }else{
                Toast.makeText(getContext(),"NULL", Toast.LENGTH_SHORT).show();
            }
        });
        binding.searchLaptop.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }
    private void removeSharedPreferences(String type){
        sharedPreferences.edit().remove(type).commit();
    }
    private void setAdapter() {
        laptops = new ArrayList<>();
        binding.rvLaptop.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new LaptopAdapter(laptops);
        adapter.setRecyclerViewLaptopClickListener(this);
        binding.rvLaptop.setAdapter(adapter);
    }
    private void viewVisible(){
        binding.searchLaptop.setVisibility(View.VISIBLE);
        binding.imgCart.setVisibility(View.VISIBLE);
    }
    private void viewGone(){
        binding.searchLaptop.setVisibility(View.GONE);
        binding.imgCart.setVisibility(View.GONE);
    }
    @Override
    public void RecyclerViewLaptopItemClick(Laptop laptop) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("laptop", laptop);
        NavHostFragment.findNavController(this).navigate(R.id.laptopDetailFragment, bundle);
    }
}
