package com.shellinglaptop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.model.LaptopList;
import com.shellinglaptop.network.LaptopApi;
import com.shellinglaptop.network.RetrofitInstance;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // verifyStoragePermissions(MainActivity.this);

        getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);


//
    }

    public static void verifyStoragePermissions(Activity activity) {

        final int REQUEST_EXTERNAL_STORAGE = 1;
       String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//
//            Uri imageUri = data.getData();
//
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//            } catch (IOException e) {
//                Log.i("TAG", "Some exception " + e);
//            }
//            if(bitmap == null){
//                Log.d("KMFG", "NGU");
//            }
//
//            Laptop laptop = new Laptop();
//            laptop.setImage(getStringImage(bitmap));
//            laptop.setName("CON CAC");
//            LaptopApi laptopApi = RetrofitInstance.getRetrofitClient().create(LaptopApi.class);
//            laptopApi.saveLaptop(laptop).enqueue(new Callback<Void>() {
//                @Override
//                public void onResponse(Call<Void> call, Response<Void> response) {
//                    if(response.isSuccessful()){
//                        Log.d("KMFG", "OKEE");
//                    }else{
//                        Log.d("KMFG", "LOL="+String.valueOf(response.errorBody()));//????
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Void> call, Throwable t) {
//                    Log.d("KMFG", "ERR="+t.getMessage());
//                }
//            });
//        }else {
//            Toast.makeText(MainActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public String getStringImage(Bitmap bmp){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
//
//        return encodedImage;
//    }
}