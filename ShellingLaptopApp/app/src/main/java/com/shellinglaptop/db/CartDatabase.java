package com.shellinglaptop.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.shellinglaptop.model.Cart;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao cartDao();

    private static CartDatabase cartDatabase;
    private static String DATABASE_NAME = "cartdb";

    public synchronized static CartDatabase getInstance(Context context){
        if(cartDatabase == null){
            cartDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    CartDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return cartDatabase;
    }
}
