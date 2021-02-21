package com.shellinglaptop.fragment.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.shellinglaptop.R;
import com.shellinglaptop.adapter.LaptopAdminAdapter;
import com.shellinglaptop.databinding.FragmentAdminLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.utils.ClickUtils;
import com.shellinglaptop.utils.ConstantUtils;
import com.shellinglaptop.viewmodel.LaptopViewModel;
import java.util.ArrayList;
import java.util.List;

public class LaptopAdminFragment extends Fragment implements ClickUtils.IRecyclerViewClickListener {

    private FragmentAdminLaptopBinding binding;
    private LaptopAdminAdapter adapter;
    private LaptopViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminLaptopBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();

        viewModel = new ViewModelProvider(this).get(LaptopViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.laptopApiCall();
        viewModel.getLaptops().observe(getViewLifecycleOwner(), laptopList -> {
            if(laptopList != null){
                adapter.setLaptops(laptopList);
            }else{
                Toast.makeText(getContext(),"NULL", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getIsDelete().observe(getViewLifecycleOwner(), isDelete->{
            if(isDelete){
                binding.progressBar.setVisibility(View.VISIBLE);
            }else{
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void setAdapter(){
        List<Laptop> laptops = new ArrayList<>();
        adapter = new LaptopAdminAdapter(laptops, this);
        binding.rvAdminLaptop.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void recyclerViewItemClick(Laptop laptop) {
        Bundle bundle = new Bundle();
        laptop.setTypeUpdate(ConstantUtils.Update);
        bundle.putSerializable("laptop", laptop);
        NavHostFragment.findNavController(this).navigate(R.id.updateOrAddLaptopAdminFragment, bundle);
    }
    @Override
    public void btnDeleteItemClick(Laptop laptop) {
       viewModel.deleteLaptopApiCall(laptop);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        NavHostFragment.findNavController(this).popBackStack();
    }
}
