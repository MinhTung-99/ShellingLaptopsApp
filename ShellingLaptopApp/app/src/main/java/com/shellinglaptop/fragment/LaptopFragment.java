package com.shellinglaptop.fragment;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shellinglaptop.R;
import com.shellinglaptop.adapter.LaptopAdapter;
import com.shellinglaptop.databinding.FragmentLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.model.LaptopList;
import com.shellinglaptop.viewmodel.LaptopViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LaptopFragment extends Fragment implements LaptopAdapter.RecyclerViewLaptopClickListener {

    private LaptopViewModel viewModel;
    private FragmentLaptopBinding binding;
    private LaptopAdapter adapter;
    private List<Laptop> laptops;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding  = FragmentLaptopBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewGone();
        setAdapter();

        viewModel = new ViewModelProvider(this).get(LaptopViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.laptopApiCall();
        viewModel.getLaptops().observe(getViewLifecycleOwner(), laptopList -> {
            if(laptopList != null){
                viewVisible();
                binding.progressBar.setVisibility(View.GONE);

                laptops = laptopList.getLaptops();
                adapter.setLaptops(laptops);
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
