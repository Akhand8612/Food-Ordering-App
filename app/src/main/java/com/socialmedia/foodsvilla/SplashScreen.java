package com.socialmedia.foodsvilla;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.splash_screen);

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {

        Intent iHome = new Intent(SplashScreen.this, MainActivity.class);

        startActivity(iHome);
finish();

    }
}, 4000);
    }
}
