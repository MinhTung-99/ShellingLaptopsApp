package com.shellinglaptop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation ani = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        findViewById(R.id.txt_animation).startAnimation(ani);

        Handler handler = new Handler();
        final Runnable r = () ->
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
        handler.postDelayed(r, 3000);
    }
}