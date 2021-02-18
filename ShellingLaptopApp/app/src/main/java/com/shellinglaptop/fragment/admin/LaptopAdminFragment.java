package com.shellinglaptop.fragment.admin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.shellinglaptop.R;
import com.shellinglaptop.adapter.LaptopAdapter;
import com.shellinglaptop.adapter.LaptopAdminAdapter;
import com.shellinglaptop.databinding.FragmentAdminLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.model.LaptopList;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;
import com.shellinglaptop.utils.ClickUtils;
import com.shellinglaptop.utils.ConstantUtils;
import com.shellinglaptop.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaptopAdminFragment extends Fragment implements ClickUtils.IRecyclerViewClickListener {

    private FragmentAdminLaptopBinding binding;
    private LaptopAdminAdapter adapter;
    private List<Laptop> laptops;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminLaptopBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        laptops = new ArrayList<>();

        LaptopApi laptopApi = RetrofitInstance.getRetrofitClient().create(LaptopApi.class);
        laptopApi.getLaptops().enqueue(new Callback<LaptopList>() {
            @Override
            public void onResponse(Call<LaptopList> call, Response<LaptopList> response) {
                if(response.isSuccessful()){
                    laptops = response.body().getLaptops();
                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<LaptopList> call, Throwable t) {
                Log.d("KMFG", "ERR=" + t.getMessage());
            }
        });

        binding.imgAdd.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            Laptop laptop = new Laptop();
            laptop.setTypeUpdate(ConstantUtils.ADD);
            bundle.putSerializable("laptop", laptop);
            NavHostFragment.findNavController(this).navigate(R.id.updateOrAddLaptopAdminFragment, bundle);
        });
    }

    private void setAdapter(){
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
        LaptopApi laptopApi = RetrofitInstance.getRetrofitClient().create(LaptopApi.class);
        laptopApi.deleteLaptop(laptop, UserUtils.userName, UserUtils.password).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("KMFG", "OKEE=");
                }else{
                    Log.d("KMFG", "FAILED="+response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("KMFG", "ERR="+t.getMessage());
            }
        });
    }
}
