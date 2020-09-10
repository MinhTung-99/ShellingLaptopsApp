package com.example.shellinglaptopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shellinglaptopapp.api.LaptopApi
import com.example.shellinglaptopapp.api.LaptopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get retrofit
        val repository = LaptopRepository(LaptopApi())
        GlobalScope.launch (Dispatchers.Main) {
            val laptop = repository.getLaptops()
            Toast.makeText(this@MainActivity, laptop.informationLaptop!![0].brand!!.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}