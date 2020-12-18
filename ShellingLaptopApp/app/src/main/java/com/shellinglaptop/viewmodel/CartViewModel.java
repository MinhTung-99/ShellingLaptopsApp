package com.shellinglaptop.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shellinglaptop.db.CartDao;
import com.shellinglaptop.db.CartDatabase;
import com.shellinglaptop.model.Cart;

import java.util.List;

public class CartViewModel extends ViewModel {

    private CartDao cartDao;
    private MutableLiveData<List<Cart>> carts;

    public CartViewModel() {
        carts = new MutableLiveData<>();
    }

    public void setContext(Context context) {
        cartDao = CartDatabase.getInstance(context).cartDao();
    }

    public MutableLiveData<List<Cart>> getCarts(){
        carts.postValue(cartDao.getCart());
        return carts;
    }
}
