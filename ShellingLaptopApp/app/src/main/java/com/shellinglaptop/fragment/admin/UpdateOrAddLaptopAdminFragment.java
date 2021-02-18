package com.shellinglaptop.fragment.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.shellinglaptop.databinding.FragmentAdminUpdateOrAddLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;
import com.shellinglaptop.utils.ConstantUtils;
import com.shellinglaptop.utils.ImageUtils;
import com.shellinglaptop.utils.UserUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateOrAddLaptopAdminFragment extends Fragment {

    private FragmentAdminUpdateOrAddLaptopBinding binding;
    private LaptopApi laptopApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminUpdateOrAddLaptopBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        laptopApi = RetrofitInstance.getRetrofitClient().create(LaptopApi.class);

        Laptop laptop = (Laptop) getArguments().getSerializable("laptop");
        if(laptop.getTypeUpdate() == ConstantUtils.Update){
            binding.btnUpdateOrAdd.setText("UPDATE");
            setEditText(laptop);
        }else{
            binding.btnUpdateOrAdd.setText("ADD");
        }
        binding.imgAdminLaptop.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1);
        });
        binding.btnUpdateOrAdd.setOnClickListener(v -> {
            setLaptop(laptop);
            if(laptop.getTypeUpdate() == ConstantUtils.Update){
                laptopApi.updateLaptop(laptop, laptop.getLaptopId(),UserUtils.userName, UserUtils.password).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful()){
                            Log.d("KMFG", "OKEE="+response.body());
                        }else{
                            Log.d("KMFG", "FAILED="+response.errorBody());
                        }
                    }
                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.d("KMFG", "ERR="+t.getMessage());
                    }
                });
            }else{
                laptopApi.saveLaptop(laptop, UserUtils.userName, UserUtils.password).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful()){
                            Log.d("KMFG", "OKEE="+response.body());
                        }else{
                            Log.d("KMFG", "FAILED="+response.errorBody());
                        }
                    }
                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.d("KMFG", "ERR="+t.getMessage());
                    }
                });
            }
        });
    }

    private void setEditText(Laptop laptop){
        Bitmap bitmap = ImageUtils.convertStringToBitmap(laptop.getImage());
        binding.imgAdminLaptop.setImageBitmap(bitmap);
        binding.edtName.setText(laptop.getName());
        if(laptop.getPrice() == null)
            laptop.setPrice(0L);
        binding.edtPrice.setText(laptop.getPrice().toString());
        binding.edtSale.setText(laptop.getSale());
        binding.edtDescription.setText(laptop.getDescription());
    }

    void setLaptop(Laptop laptop){
        laptop.setImage(strImage);
        laptop.setName(binding.edtName.getText().toString());
        laptop.setPrice(Long.parseLong(binding.edtPrice.getText().toString()));
        laptop.setSale(binding.edtSale.getText().toString());
        laptop.setDescription(binding.edtDescription.getText().toString());
    }

    String strImage = "";
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            try {
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                binding.imgAdminLaptop.setImageBitmap(bitmap);
                strImage = getStringImage(bitmap);
            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }else {
            Toast.makeText(getContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
