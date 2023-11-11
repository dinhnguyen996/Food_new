package com.techja.fodenew.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techja.fodenew.R;

import fragment.FragmentLogin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //gán fragemtnlogin vào main
        FragmentLogin fragmentLogin=new FragmentLogin();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_holder,fragmentLogin)
                .commit();
    }
}
