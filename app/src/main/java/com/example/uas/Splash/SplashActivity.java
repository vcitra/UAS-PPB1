package com.example.uas.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.uas.MainActivity;
import com.example.uas.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed( new Runnable() {
        @Override
        public void run() {
            Intent intentRadio = new Intent(SplashActivity.this, MainActivity.class);

            startActivity(intentRadio);
        }
        }, 1000);
    }
}