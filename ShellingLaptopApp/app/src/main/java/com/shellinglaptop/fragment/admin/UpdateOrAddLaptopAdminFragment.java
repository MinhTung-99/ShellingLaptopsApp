package com.shellinglaptop.fragment.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.shellinglaptop.databinding.FragmentAdminUpdateOrAddLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.utils.ConstantUtils;
import com.shellinglaptop.utils.ImageUtils;
import com.shellinglaptop.viewmodel.LaptopViewModel;
import java.io.IOException;

public class UpdateOrAddLaptopAdminFragment extends Fragment {

    private FragmentAdminUpdateOrAddLaptopBinding binding;
    private LaptopViewModel viewModel;
    private Laptop laptop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminUpdateOrAddLaptopBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.progressBar.setVisibility(View.GONE);
        binding.btnUpdateOrAdd.setEnabled(true);

        viewModel = new ViewModelProvider(this).get(LaptopViewModel.class);
        binding.setViewmodel(viewModel);
        laptop = (Laptop) getArguments().getSerializable("laptop");
        binding.setLaptop(laptop);
        viewModel.setLaptop(laptop);
        viewModel.setContext(getContext());
        if(laptop.getTypeUpdate() == ConstantUtils.Update){
            binding.btnUpdateOrAdd.setText("UPDATE");
        }else{
            binding.btnUpdateOrAdd.setText("ADD");
        }
        binding.imgAdminLaptop.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1);
        });
        viewModel.getIsUpdateOrAdd().observe(getViewLifecycleOwner(), isUpdateOrAdd->{
            if(!isUpdateOrAdd){
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnUpdateOrAdd.setEnabled(false);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            try {
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                binding.imgAdminLaptop.setImageBitmap(bitmap);
                String strImage = ImageUtils.getStringImage(bitmap);
                laptop.setImage(strImage);
                viewModel.setLaptop(laptop);
            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }else {
            Toast.makeText(getContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}
