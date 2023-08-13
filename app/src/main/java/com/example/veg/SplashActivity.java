package com.example.veg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
public class SplashActivity extends AppCompatActivity {
    SessionManager sessionManager;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        context = SplashActivity.this;
        sessionManager = new SessionManager(context);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                  startActivity(new Intent(SplashActivity.this, LoginActivity.class));
           /*     if (sessionManager.isLoggedIn()) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));


                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }*/
            }
        }, 2000);
    }

}