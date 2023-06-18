package com.example.loginsignup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText loginEmail, loginPassword;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textview1 = findViewById(R.id.clickable1);
        TextView textview2 = findViewById(R.id.clickable2);

        String forgotPassword = "Forgot Password?";
        String registerHere = "Don’t have an account? Register Here";

        SpannableString ss2 = new SpannableString(registerHere);
        SpannableString ss1 = new SpannableString(forgotPassword);

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
//                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                startActivityForResult(new Intent(LoginActivity.this, SignUpActivity.class), 1);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                String hexColor = "#439FD9"; // Replace with your hex color value
                int color = Color.parseColor(hexColor);
                ds.setColor(color);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                String url = "https://www.youtube.com/watch?v=xvFZjo5PgG0";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                String hexColor = "#439FD9"; // Replace with your hex color value
                int color = Color.parseColor(hexColor);
                ds.setColor(color);
                ds.setUnderlineText(false);
            }
        };

        ss1.setSpan(clickableSpan1, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(clickableSpan2, 23, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textview1.setText(ss1);
        textview1.setMovementMethod(LinkMovementMethod.getInstance());

        textview2.setText(ss2);
        textview2.setMovementMethod(LinkMovementMethod.getInstance());


        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.password_login);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(LoginActivity.this, "Login Successful😎", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "Email or Password Wrong😥", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPassword.setError("Password cannot be empty😥");
                    }
                }else if (email.isEmpty()){
                    loginEmail.setError("Email cannot be empty😥");
                }else {
                    loginEmail.setError("Please enter valid email😥");
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Reset Form Field di Login
        loginEmail.setText("");
        loginPassword.setText("");
    }
}