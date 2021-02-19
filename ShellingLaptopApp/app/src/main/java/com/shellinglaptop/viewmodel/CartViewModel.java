package com.shellinglaptop.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shellinglaptop.db.CartDao;
import com.shellinglaptop.db.CartDatabase;
import com.shellinglaptop.model.Cart;
import com.shellinglaptop.model.Order;
import com.shellinglaptop.network.OrderApi;
import com.shellinglaptop.network.RetrofitInstance;
import com.shellinglaptop.utils.UserUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {

    private CartDao cartDao;
    private MutableLiveData<List<Cart>> carts;
    private Context context;
    private SharedPreferences sharedPreferences;

    public CartViewModel() {
        carts = new MutableLiveData<>();
    }

    public void setContext(Context context) {
        cartDao = CartDatabase.getInstance(context).cartDao();
        this.context = context;
    }
    public MutableLiveData<List<Cart>> getCarts(){
        carts.postValue(cartDao.getCart());
        return carts;
    }
    public void saveOrderApiCall(Cart cart){
        Order order = getOrder(cart);

        OrderApi orderApi = RetrofitInstance.getRetrofitClient().create(OrderApi.class);
        orderApi.saveOrder(order, UserUtils.userName, UserUtils.password).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    if(response.body()){
                        Toast.makeText(context, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.d("KMFG", "FAILED"+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("KMFG", "ERR="+ t.getMessage());
            }
        });
    }
    private Order getOrder(Cart cart){
        sharedPreferences = context.getSharedPreferences(UserUtils.MY_PREFERENCES, Context.MODE_PRIVATE);
        Order order = new Order();
        order.setCount(cart.getCount());
        order.setTotalMoney(cart.getTotalMoneyStr());
        order.setLaptopId(cart.getLaptopId());

        if(sharedPreferences.contains(UserUtils.USER_NAME)){
            Long userId = sharedPreferences.getLong(UserUtils.USER_ID, -1);
            UserUtils.userName = sharedPreferences.getString(UserUtils.USER_NAME, "FAILED");
            UserUtils.password = sharedPreferences.getString(UserUtils.PASSWORD, "FAILED");
            order.setUserId(userId);
        }

        return order;
    }
}
