package com.shellinglaptop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation ani = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ani_splash);
        findViewById(R.id.txt_animation).startAnimation(ani);

        Handler handler = new Handler();
        final Runnable r = () ->{
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        };
        handler.postDelayed(r, 3000);
    }
}