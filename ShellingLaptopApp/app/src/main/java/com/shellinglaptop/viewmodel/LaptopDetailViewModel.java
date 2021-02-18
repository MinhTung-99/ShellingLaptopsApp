package com.shellinglaptop.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.shellinglaptop.R;
import com.shellinglaptop.db.CartDao;
import com.shellinglaptop.db.CartDatabase;
import com.shellinglaptop.model.Cart;
import com.shellinglaptop.model.Laptop;

public class LaptopDetailViewModel extends ViewModel {

    private MutableLiveData<Integer> count;
    private int _count = 1;
    private Laptop laptop;
    private Context context;

    public MutableLiveData<Integer> getCount() {
        return count;
    }

    public void setLaptops(Laptop laptop) {
        this.laptop = laptop;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LaptopDetailViewModel() {
        count = new MutableLiveData<>();
        count.postValue(_count);
    }

    public void txtIncreaseOnClick(View view){
        _count++;
        if(_count > 9){
            _count = 9;
        }
        count.postValue(_count);
    }

    public void txtReductionOnClick(View view){
        _count--;
        if(_count < 1){
            _count = 1;
        }
        count.postValue(_count);
    }

    public void btnAddCartOnClick(View view){
        CartDao cartDao = CartDatabase.getInstance(context).cartDao();
        Cart cart = new Cart();
        cart.setCart(laptop, cart);
        cart.setCount(count.getValue());
        cart.setTotalMoney(laptop.getPrice()*count.getValue());
        cartDao.insertCart(cart);

        Navigation.findNavController(view).navigate(R.id.cartFragment);
    }
}
