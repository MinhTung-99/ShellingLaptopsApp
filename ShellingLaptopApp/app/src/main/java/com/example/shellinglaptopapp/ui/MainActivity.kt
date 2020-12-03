package com.example.shellinglaptopapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.databinding.ActivityMainBinding
import com.example.shellinglaptopapp.ui.laptops.LaptopFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.main = viewModel

        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}