package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    private TextView username;
    private Button logout;
    private FloatingActionButton fab_back ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        username = findViewById(R.id.username);
        logout = findViewById(R.id.btn_logOut);
        fab_back = findViewById(R.id.fab_back_home);

        fab_back.setOnClickListener(view -> {
            Intent gotoHome = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(gotoHome);
            finish();
        });

        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            // Display the message
            String message = userEmail;
            // Use the 'message' variable to set the text of a TextView or any other UI element on your home page
            username.setText(message);
        }

        logout.setOnClickListener(view -> {
            Intent logout = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(logout);
            finish();
        });

    }
}