package com.example.getinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        NewsFragment fragment = new NewsFragment();
        FragmentManager manager = getSupportFragmentManager();
         manager.beginTransaction().add(R.id.mainLayout2,fragment).setCustomAnimations(R.anim.anim1,R.anim.anim2,R.anim.anim1,R.anim.anim2).commit();



    }
}
