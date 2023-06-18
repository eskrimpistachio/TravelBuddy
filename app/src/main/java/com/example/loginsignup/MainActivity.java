package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.loginsignup.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private FloatingActionButton fab_form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fab_form = findViewById(R.id.btn_form);
        fab_form.setOnClickListener(view -> {
            Intent gotoForm = new Intent(MainActivity.this, FormActivity.class);
            startActivity(gotoForm);
            finish();
        });
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        replaceFragment(new HomeFragment());
//
//        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.menu_home:
//                    replaceFragment(new HomeFragment());
//                    break;
//                case R.id.menu_search:
//                    replaceFragment(new SearchFragment());
//                    break;
//                case R.id.menu_profile:
//                    replaceFragment(new ProfileFragment());
//                    break;
//
//            }
//            return false;
//        });


    }
//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout,fragment);
//        fragmentTransaction.commit();
//    }
}