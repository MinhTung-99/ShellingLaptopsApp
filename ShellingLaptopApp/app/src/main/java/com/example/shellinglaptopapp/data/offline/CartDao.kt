package com.example.shellinglaptopapp.data.offline

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shellinglaptopapp.data.model.Cart

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cart: Cart)

    @Query("SELECT * FROM table_cart")
    fun getAllCarts(): LiveData<List<Cart>>
}