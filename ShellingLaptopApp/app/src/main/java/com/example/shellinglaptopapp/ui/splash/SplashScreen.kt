package com.example.shellinglaptopapp.ui.splash

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val ani = AnimationUtils.loadAnimation(applicationContext, R.anim.animation)
        txt_animation.startAnimation(ani)

        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            txt_animation.clearAnimation()
            finish()
        },2000)

    }
}