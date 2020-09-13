package com.example.shellinglaptopapp.data.offline

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shellinglaptopapp.data.model.Cart

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cart: Cart)

    @Query("SELECT * FROM table_cart")
    fun getAllCarts(): LiveData<List<Cart>>

    @Delete
    suspend fun delete(cart: Cart)
}