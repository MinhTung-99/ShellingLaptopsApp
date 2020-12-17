package com.shellinglaptop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class LaptopFragment extends Fragment implements LaptopAdapter.RecyclerViewLaptopClickListener {

    private LaptopViewModel viewModel;
    private FragmentLaptopBinding binding;
    private RecyclerView recyclerView;
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

        addView(view);
        setAdapter();

        viewModel = new ViewModelProvider(this).get(LaptopViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.laptopApiCall();
        viewModel.getLaptops().observe(getViewLifecycleOwner(), new Observer<LaptopList>() {
            @Override
            public void onChanged(LaptopList laptopList) {
                if(laptopList != null){
                    laptopList.getLaptops().get(0).setImageUrl("http://192.168.4.102:8080/getimage/Android.jpg");
                    laptops = laptopList.getLaptops();
                    adapter.setLaptops(laptops);
                    Toast.makeText(getContext(),laptopList.getLaptops().get(0).getImageUrl(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"NULL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new LaptopAdapter(laptops);
        adapter.setRecyclerViewLaptopClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void addView(View view) {
        recyclerView = view.findViewById(R.id.rv_laptop);
        laptops = new ArrayList<>();
    }

    @Override
    public void RecyclerViewLaptopItemClick(Laptop laptop) {
        Toast.makeText(getContext(), laptop.getCardGraphic(), Toast.LENGTH_SHORT).show();

        NavHostFragment.findNavController(this).navigate(R.id.laptopDetailFragment);
    }
}
