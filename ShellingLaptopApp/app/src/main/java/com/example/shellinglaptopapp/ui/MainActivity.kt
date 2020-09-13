package com.example.shellinglaptopapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

        val fragmentTransaction = supportFragmentManager?.beginTransaction()
        val laptopsFragment = LaptopFragment()
        fragmentTransaction.replace(R.id.fragment, laptopsFragment)
        fragmentTransaction.commit()
    }

    fun setVisibleToolBar(check: Boolean){
        if(check){
            tool_bar.visibility = View.VISIBLE
        }else {
            tool_bar.visibility = View.GONE
        }
    }
}