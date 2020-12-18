package com.shellinglaptop.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.shellinglaptop.model.Cart;
import com.shellinglaptop.model.Laptop;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cart")
    List<Cart> getCart();

    @Insert(onConflict = REPLACE)
    void insertCart(Cart cart);

    @Delete
    void deleteCart(Cart cart);
}
