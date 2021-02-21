package com.shellinglaptop.fragment.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import androidx.navigation.Navigation;

import com.shellinglaptop.R;
import com.shellinglaptop.databinding.FragmentAdminUpdateOrAddLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.utils.ConstantUtils;
import com.shellinglaptop.utils.ImageUtils;
import com.shellinglaptop.viewmodel.LaptopViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

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
        if(laptop.getImage() == null){
            setImage(R.drawable.ic_laptop);
        }
        binding.setLaptop(laptop);
        viewModel.setLaptop(laptop);
        viewModel.setContext(getContext());
        if(laptop.getTypeUpdate() == ConstantUtils.Update){
            binding.btnUpdateOrAdd.setText("UPDATE");
        }else{
            binding.btnUpdateOrAdd.setText("ADD");
        }
        binding.imgAdminLaptop.setOnClickListener(v -> {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .setMinCropResultSize(500,500)
                    .setMaxCropResultSize(1000,1000)
                    .start(getContext(), this);
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
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == getActivity().RESULT_OK){
                setImage(result.getUri());
                binding.setLaptop(laptop);
            }else {
                Navigation.findNavController(getView()).popBackStack();
            }
        }
    }

    private void setImage(Object image){
        Bitmap bitmap = null;
        if(image instanceof Integer){
            bitmap = BitmapFactory.decodeResource(getResources(), (Integer) image);
        }else if(image instanceof Uri){
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), (Uri) image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String strImage = ImageUtils.getStringImage(bitmap);
        laptop.setImage(strImage);
    }
}
