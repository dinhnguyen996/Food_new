package com.techja.fodenew.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.techja.fodenew.R;

public class SplashActivity extends AppCompatActivity {
    private static final String KEY="CHECK_FIST_INSTALL_APP";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkFistInstallApp();
    }

    private void checkFistInstallApp() {
        MySharedPreferences mySharedPreferences=new MySharedPreferences(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mySharedPreferences.getBooleanValue(KEY)){
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(getApplicationContext(), BroadScreenActivity.class);
                    startActivity(intent);
                    mySharedPreferences.putBooleanValue(KEY,true);
                }
            }
        },1000);
    }
}