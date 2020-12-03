package com.example.shellinglaptopapp.data.offline

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shellinglaptopapp.data.model.Cart

@Database(entities = [Cart::class], version = 1, exportSchema = false)
abstract class CartRoomDatabase: RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        private var INSTANCE: CartRoomDatabase? = null

        fun getDatabase(context: Context): CartRoomDatabase{

            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                synchronized(CartRoomDatabase::class){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CartRoomDatabase::class.java,
                        "table_cart"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance

                    return INSTANCE!!
                }
            }
        }
    }
}